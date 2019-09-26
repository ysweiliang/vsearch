import Vue from 'vue'
import Vuex from 'vuex'


Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        userInfo: {
            name: window.localStorage.getItem('userInfo' || '[]') == null ? '未登录' : JSON.parse(window.localStorage.getItem('userInfo' || '[]')).name,
            userface: window.localStorage.getItem('userInfo' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('userInfo' || '[]')).id
        }
    },
    mutations: {
        login(state, userInfo) {
            state.userInfo = userInfo;
            window.localStorage.setItem('userInfo', JSON.stringify(userInfo));
        },
        logout() {
            window.localStorage.removeItem('userInfo');
        }
    },
    actions: {}
});
