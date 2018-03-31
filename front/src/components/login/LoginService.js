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
        if (errorCallback) {
          errorCallback(error)
        }
      })
  }

  createNewAccount (accountData, successCallback, errorCallback) {
    this.axios.post('/registration', accountData)
      .then(function (response) {
        if (successCallback) {
          successCallback(response)
        }
      })
      .catch(function (error) {
        if (errorCallback) {
          errorCallback(error)
        }
      })
  }
}

export let loginService = new LoginService()
