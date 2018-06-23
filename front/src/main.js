// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'jquery/dist/jquery'
import 'popper.js'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap'
import DateUtils from './utils/DateUtils'

Vue.config.productionTip = false

Vue.filter('formatdate', function (value) {
  let date = DateUtils.toJsDate(value)
  return new Intl.DateTimeFormat('fr-FR').format(date)
})

Vue.filter('formatstatut', function (value) {
  return value === 'ONGOING' ? 'En cours' : 'Fermée'
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: {App}
})
