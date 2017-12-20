// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import 'bootstrap-datepicker/dist/js/bootstrap-datepicker'
import 'bootstrap-datepicker/dist/css/bootstrap-datepicker3.css'
import './assets/iconic/css/open-iconic-bootstrap.css'

Vue.config.productionTip = false

Vue.filter('formatdate', function (value) {
  return value !== null ? value[2] + '/' + value[1] + '/' + value[0] : ''
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: {App}
})
