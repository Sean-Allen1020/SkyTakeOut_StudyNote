const { createApp } = Vue
const { ElMessage } = ElementPlus
createApp({
    data() {
        return {

        }
    },

    methods: {
        testPage(){
            console.log("testPage loaded");
            window.location.replace(`${window.location.origin}/testPage.html`);
        },

        logout(){
            console.log("uploadJS loaded");
            window.location.replace(`${window.location.origin}/login.html`);
        }
    },
    mounted() {
    },
}).mount('#app')