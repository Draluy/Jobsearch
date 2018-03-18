import Vue from 'vue'
import Router from 'vue-router'
import Applications from '@/components/applications/Applications'
import Companies from '@/components/companies/Companies'
import Contacts from '@/components/contacts/Contacts'
import Login from '@/components/login/Login'
import {RestService} from '../components/global/RestService'
import store from '../components/global/Store'

Vue.use(Router)

let router = new Router({
  routes: [
    {path: '/applications', component: Applications, meta: {auth: true}},
    {path: '/companies', component: Companies, meta: {auth: true}},
    {path: '/contacts', component: Contacts, meta: {auth: true}},
    {path: '/', component: Applications, meta: {auth: true}},
    {path: '/login', component: Login, meta: {auth: false}}
  ]
})

router.beforeEach((to, from, next) => {
  const authRequired = to.matched.some((route) => route.meta.auth)

  if (!authRequired) {
    next()
    return
  }

  let restService = new RestService()
  restService.axios.get('/hello')
    .then((response) => {
      if (response.data === 'hello') {
        store.init()
        next()
      } else {
        next('/login')
      }
    })
    .catch((response) => {
      next('/login')
    })
})

export default router
