import {RestService} from '../global/RestService'
import Vue from 'vue'
import VueSession from 'vue-session'

Vue.use(VueSession)

class LoginService extends RestService {
  login (username, password, router, errorCallback) {
    const data = new FormData()
    data.append('username', username)
    data.append('password', password)

    let session = Vue.prototype.$session
    this.axios.post('/login', data)
      .then(function (response) {
        if (response.data) {
          session.start()
          session.set('authenticated', true)
          router.push('companies')
        } else {
          router.push('login')
        }
      })
      .catch(function (error) {
        console.log('error on login', error)
        if (errorCallback) {
          errorCallback(error)
          router.push('login')
        }
      })
  }
}

export let loginService = new LoginService()
