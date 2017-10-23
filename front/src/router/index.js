import Vue from 'vue'
import Router from 'vue-router'
import Appointments from '@/components/Appointments'
import Companies from '@/components/companies/Companies'
import Contacts from '@/components/Contacts'
import Login from '@/components/Login'
import axios from 'axios'

Vue.use(Router)

let router = new Router({
  routes: [
    {path: '/appointments', component: Appointments},
    {path: '/companies', component: Companies},
    {path: '/contacts', component: Contacts},
    {path: '/', component: Appointments},
    {path: '/login', component: Login}
  ]
})

router.beforeEach((to, from, next) => {
  console.log('to.path', to.path)
  if (to.path === '/login') {
    console.log('If login stop here')
    next()
    return
  }

  axios.get('http://localhost:8080/hello').then(
    (response) => {
      console.log('response', response.data)
      if (response.data === 'hello') {
        console.log('next', to)
        next()
      } else {
        console.log('gogot login')
        next('/login')
      }
    }
  )
})

export default router
