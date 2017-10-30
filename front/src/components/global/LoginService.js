import axios from 'axios'
import {RestService} from '../global/RestService'
import Vue from 'vue'
import VueSession from 'vue-session'

Vue.use(VueSession)

class LoginService extends RestService {
  login (username, password, router, errorCallback) {
    console.log('url=', this.baseUrl + '/login')
    console.log('paylof', {
      username: username,
      password: password
    })

    const data = new FormData()
    data.append('username', username)
    data.append('password', password)

    let session = Vue.prototype.$session
    axios.post(this.baseUrl + '/login', data)
      .then(function (response) {
        if (response.data === 'registration') {
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
