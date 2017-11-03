import {RestService} from '../global/RestService'

class ApplicationService extends RestService {
  getAllApplications (setApplications) {
    return this.axios.get('/application')
      .then(({data}) => {
        setApplications(data)
      })
      .catch((response) => console.log('error', response))
  }

  saveApplication (application, callbackSuccess, callbackError) {
    return this.axios.post('/application', application)
      .then(callbackSuccess)
      .catch(callbackError)
  }

  deleteApplication (application, callback) {
    return this.axios.delete('/application/' + application.id)
      .then(callback)
      .catch(function (error) {
        if (callback) callback(error)
      })
  }
}

export let applicationService = new ApplicationService()
