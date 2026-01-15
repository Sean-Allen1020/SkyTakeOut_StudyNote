const { createApp } = Vue
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

            console.log('login response:', res.data); //在控制台确认结构

            //登录成功判定，并获取token
            if(res.data.code === 1){  //login success
                //token存入变量
                const token = res.data.token;
                if(!token){
                    alert("login success, but no token from backend!");
                    return;
                }
                //token存入库里，用于拦截验证等
                localStorage.setItem('token', token);
                // 把 JWT 放到 axios 的默认请求头里（Authorization: Bearer xxx）
                // 这样后续所有 axios 请求都会自动携带 token，后端拦截器才能识别用户身份
                axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;

                alert(res.data.msg) //login success 表示
                //系统页面跳转
                // window.location.href='./depts.html';
                window.location.replace(`${window.location.origin}/upload.html`);
            }else{
                alert(res.data.msg);//login fail 表示
            }
        }
    },
    mounted() {
    },
}).mount('#app')