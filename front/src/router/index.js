import Vue from 'vue'
import Router from 'vue-router'
import VueSession from 'vue-session'
import Appointments from '@/components/Appointments'
import Companies from '@/components/companies/Companies'
import Contacts from '@/components/Contacts'
import Login from '@/components/Login'

Vue.use(Router)
Vue.use(VueSession)

let router = new Router({
  routes: [
    {path: '/appointments', component: Appointments, meta: {auth: true}},
    {path: '/companies', component: Companies, meta: {auth: true}},
    {path: '/contacts', component: Contacts, meta: {auth: true}},
    {path: '/', component: Appointments, meta: {auth: true}},
    {path: '/login', component: Login, meta: {auth: false}}
  ]
})

router.beforeEach((to, from, next) => {
  let session = Vue.prototype.$session
  const authRequired = to.matched.some((route) => route.meta.auth)
  const authed = session.get('authenticated')

  console.log('to', to.path)
  console.log('authRequired', authRequired)
  console.log('authed', authed)

  if (authRequired && !authed) {
    next('/login')
  } else {
    next()
  }
})

export default router
