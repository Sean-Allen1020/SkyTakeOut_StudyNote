const { createApp } = Vue
const { ElMessage } = ElementPlus
createApp({
    data() {
        return {

        }
    },

    methods: {
        logout(){
            console.log("uploadJS loaded");
            window.location.replace(`${window.location.origin}/register.html`);
        }
    },
    mounted() {
    },
}).mount('#app')