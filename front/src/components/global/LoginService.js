import axios from 'axios'
import {RestService} from '../global/RestService'

class LoginService extends RestService {
  login (username, password, router, errorCallback) {
    console.log('url=', this.baseUrl + '/login')

    axios.post(this.baseUrl + '/login', {
      username: username,
      password: password
    })
      .then(function (response) {
        console.log('redirectingt o companies')
        router.push('companies')
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
