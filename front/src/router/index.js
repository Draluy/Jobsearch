import Vue from 'vue'
import Router from 'vue-router'
import Appointments from '@/components/Appointments'
import Companies from '@/components/Companies'
import Contacts from '@/components/Contacts'

Vue.use(Router)

export default new Router({
  routes: [
    {path: '/appointments', component: Appointments},
    {path: '/companies', component: Companies},
    {path: '/contacts', component: Contacts},
    {path: '/', component: Appointments}
  ]
})
