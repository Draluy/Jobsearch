// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'jquery/dist/jquery'
import 'popper.js'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap'

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
