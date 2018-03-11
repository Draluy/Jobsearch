import {RestService} from '../global/RestService'

class ApplicationService extends RestService {
  getAllApplications (setApplications) {
    return this.axios.get('/application')
      .then(({data}) => {
        setApplications(data)
      })
      .catch((response) => console.log('error', response))
  }

  saveApplication (application, resume, callbackSuccess, callbackError) {
    var data = new FormData()
    console.log(application)
    data.append('application', JSON.stringify(application))
    if (resume) {
      data.append('resume', resume)
    }

    return this.axios.post('/application', data)
      .then(callbackSuccess)
      .catch(callbackError)
  }

  deleteApplication (application, callback, callbackError) {
    return this.axios.delete('/application/' + application.id)
      .then(callback)
      .catch(callbackError)
  }
}

export let applicationService = new ApplicationService()
