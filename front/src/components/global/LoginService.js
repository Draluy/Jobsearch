import {RestService} from '../global/RestService'

class LoginService extends RestService {
  login (username, password, router, errorCallback) {
    const data = new FormData()
    data.append('username', username)
    data.append('password', password)

    this.axios.post('/login', data)
      .then(function (response) {
        if (response.data === 'hello') {
          router.push('applications')
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
