import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import routes from './routes'
import store from './store'
import { getRequest } from './utils/api'
import { postRequest } from './utils/api'
import { deleteRequest } from './utils/api'
import { putRequest } from './utils/api'

Vue.config.productionTip = false

Vue.use(VueRouter)
Vue.use(ElementUI)
Vue.prototype.getRequest = getRequest
Vue.prototype.postRequest = postRequest
Vue.prototype.deleteRequest = deleteRequest
Vue.prototype.putRequest = putRequest

const router = new VueRouter({
  //mode: 'history',
  routes
})
const routerPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(error => error)
}

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
