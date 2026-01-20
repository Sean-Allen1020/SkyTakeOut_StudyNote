// json/app.js

const { createApp } = Vue;
const { createRouter, createWebHashHistory } = VueRouter;

// ========== 1) Axios：自动携带 token（除 /login 外） ==========
axios.interceptors.request.use(config => {
    const url = config.url || "";

    // 登录接口不需要带 token
    if (url.includes("/login")) return config;

    const token = localStorage.getItem("token");
    if (token) {
        config.headers = config.headers || {};
        config.headers.token = token; // 你后端 Filter 读的是 request.getHeader("token")
    }
    return config;
}, err => Promise.reject(err));

// 如果后端返回 401：自动踢回登录页（模拟真实项目体验）
axios.interceptors.response.use(res => res, err => {
    const status = err?.response?.status;
    if (status === 401) {
        localStorage.removeItem("token");
        // 这里不能用 this.$router，所以直接操作 hash
        location.hash = "#/login";
    }
    return Promise.reject(err);
});

// ========== 2) 三个“页面”组件（由原来的 html 合并而来） ==========

const LoginView = {
    template: `
    <div>
      <main class="card">
        <h1>会社管理システム・アカウント</h1>
        <p class="sub">操作：新規登録、ログイン、パスワード変更。</p>
        <div class="btn-row">
          <a class="btn link" href="javascript:void(0)">新規登録</a>
          <button @click="openLogin" class="btn primary">ログイン</button>
          <button class="btn" type="button">パスワード変更</button>
        </div>
      </main>

      <!-- ログイン对话框 -->
      <dialog ref="loginDia">
        <h3 class="dg-title">ログイン</h3>
        <div class="form">
          <div class="field">
            <label>アカウント名</label>
            <input class="input" v-model="logInfo.username" autocomplete="username" />
          </div>
          <div class="field">
            <label>パスワード</label>
            <input class="input" v-model="logInfo.password" type="password" autocomplete="current-password" />
          </div>
          <div class="split"></div>
          <div class="actions">
            <button class="btn link" @click="closeLogin" type="button">取消</button>
            <button class="btn primary" @click="login" type="button">ログイン</button>
          </div>
          <p v-if="errorMsg" class="hint" style="color:#e74c3c; margin:6px 0 0;">{{errorMsg}}</p>
        </div>
      </dialog>

      <!-- パスワード変更对话框（先保留占位，不做逻辑） -->
      <dialog>
        <h3 class="dg-title">パスワード変更</h3>
        <form class="form">
          <div class="field">
            <label>旧パスワード</label>
            <input class="input" name="oldPassword" type="password" autocomplete="current-password" />
          </div>
          <div class="field">
            <label>新パスワード</label>
            <input class="input" name="newPassword" type="password" autocomplete="new-password" />
          </div>
          <div class="field">
            <label>パスワード変更確認</label>
            <input class="input" name="confirmPassword" type="password" autocomplete="new-password" />
          </div>
          <div class="split"></div>
          <div class="actions">
            <button class="btn link" type="button">取消</button>
            <button class="btn primary" type="button">変更</button>
          </div>
        </form>
      </dialog>
    </div>
  `,
    data() {
        return {
            logInfo: { username: "", password: "" },
            errorMsg: ""
        };
    },
    methods: {
        openLogin() {
            this.errorMsg = "";
            this.$refs.loginDia.showModal();
        },
        closeLogin() {
            this.$refs.loginDia.close();
        },
        async login() {
            this.errorMsg = "";
            try {
                const res = await axios.post("/login", {
                    username: this.logInfo.username,
                    password: this.logInfo.password
                });

                if (res.data && res.data.code === 1) {
                    const token = res.data.data.token;
                    localStorage.setItem("token", token);
                    this.closeLogin();
                    this.$router.replace("/upload");
                } else {
                    this.errorMsg = (res.data && res.data.msg) ? res.data.msg : "ログイン失敗";
                }
            } catch (e) {
                this.errorMsg = "ログイン失敗（通信エラー）";
            }
        }
    }
};

const UploadView = {
    template: `
    <div style="padding:16px;">
      <form @submit.prevent="upload">
        <label>
          图片：
          <input type="file" name="file" accept="image/*" @change="onFileChange">
        </label>
        <button type="submit">上传</button>
      </form>

      <div v-if="img">
        <img :src="img" alt="preview" style="max-width:300px; margin-top:12px;">
      </div>

      <button @click="toTestPage">TEST PAGE</button>
      <br>
      <button @click="logout">ログアウト</button>
    </div>
  `,
    data() {
        return {
            file: null,
            img: ""
        };
    },
    methods: {
        onFileChange(e) {
            this.file = e.target.files?.[0] ?? null;
        },
        async getImg() {
            const res = await axios.get("/img");
            this.img = res.data.data;
        },
        async upload() {
            if (!this.file) return alert("请选择图片");

            const fd = new FormData();
            // 这里的 "file" 必须和后端接收字段名一致（你 input 的 name="file"）
            fd.append("file", this.file);

            // 不要手动设置 Content-Type，让浏览器自动带 boundary
            await axios.post("/upload", fd);

            await this.getImg();
        },

        async toTestPage() {
            const res = await axios.get("/testPage");
            if (res.data.code === 1) {
                this.$router.push("/testPage");
            }
        },
        logout() {
            localStorage.removeItem("token");
            this.$router.replace("/login");
        }
    },
    mounted() {
        this.getImg();
    }
};

const TestPageView = {
    template: `
    <div style="padding:16px;">
      <button @click="toUpload">アップロード</button>
      <br>
      <button @click="logout">ログアウト</button>
    </div>
  `,
    methods: {
        toUpload() {
            this.$router.push("/upload");
        },
        logout() {
            localStorage.removeItem("token");
            this.$router.replace("/login");
        }
    }
};

// ========== 3) Router：hash 模式 + 路由守卫 ==========
const routes = [
    { path: "/login", component: LoginView },
    { path: "/upload", component: UploadView, meta: { requiresAuth: true } },
    { path: "/testPage", component: TestPageView, meta: { requiresAuth: true } },
    { path: "/", redirect: "/login" }
];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

router.beforeEach((to) => {
    const token = localStorage.getItem("token");

    // 已登录就别回登录页（可选体验）
    if (to.path === "/login" && token) return "/upload";

    // 业务页必须登录
    if (to.meta.requiresAuth && !token) return "/login";

    return true;
});

// ========== 4) 启动应用 ==========
createApp({
    template: `<router-view />`
}).use(router).mount("#app");
