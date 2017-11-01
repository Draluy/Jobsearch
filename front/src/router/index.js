import Vue from 'vue'
import Router from 'vue-router'
import Appointments from '@/components/appointments/Appointments'
import Companies from '@/components/companies/Companies'
import Contacts from '@/components/Contacts'
import Login from '@/components/Login'
import {RestService} from '../components/global/RestService'

Vue.use(Router)

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
  const authRequired = to.matched.some((route) => route.meta.auth)

  console.log('to.path', to.path)
  console.log('authRequired', authRequired)
  if (!authRequired) {
    next()
    return
  }

  let restService = new RestService()
  restService.axios.get('/hello')
    .then((response) => {
      console.log('response', response)
      if (response.data === 'hello') {
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
