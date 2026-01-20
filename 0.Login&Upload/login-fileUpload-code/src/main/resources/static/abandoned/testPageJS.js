const { createApp } = Vue
const { ElMessage } = ElementPlus
createApp({
    data() {
        return {

        }
    },

    methods: {
        upload(){
            console.log("uploadJS loaded");
            window.location.replace(`${window.location.origin}/upload.html`);
        },

        logout(){
            console.log("uploadJS loaded");
            window.location.replace(`${window.location.origin}/login.html`);
        }
    },
    mounted() {
        if (!localStorage.getItem("token")) {
            window.location.replace("/login.html");
        }
    },
}).mount('#app')