const { createApp } = Vue
const { ElMessage } = ElementPlus

createApp({
    data() {
        return {
            logInfo:{
                username:'',
                password:'',
            }
        }
    },

    methods: {
        // 开关登录对话框
        openLogin(){
            this.$refs.loginDia.showModal();
        },
        closeLogin(){
            this.$refs.loginDia.close();
        },
        //登录请求及验证
        async login(){
            const res = await axios.post("/login", {
                username: this.logInfo.username,
                password: this.logInfo.password
            });

            console.log('login response:', res.data.data); //在控制台确认结构

            //登录成功判定，并获取token
            if(res.data.code === 1){  //login success
                //储存token
                const token = res.data.data.token;
                localStorage.setItem('token', token);
                //系统页面跳转
                ElMessage.success('ログイン成功');
                window.location.replace(`/upload.html`);
            }else{
                ElMessage.error(res.data.msg || "ログイン失敗");//login fail 表示
            }
        }
    },
    mounted() {
        if (!localStorage.getItem("token")) {
            window.location.replace("/login.html");
        }
    },
}).mount('#app')