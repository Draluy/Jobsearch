import {RestService} from '../global/RestService'

class ApplicationService extends RestService {
  getAllApplications (setApplications) {
    return this.axios.get('/application')
      .then(({data}) => {
        setApplications(data)
      })
      .catch((response) => console.log('error', response))
  }

  getApplication (appId) {
    return this.axios.get('/application/' + appId)
  }

  saveApplication (application, resume, coverLetter) {
    var data = new FormData()
    data.append('application', JSON.stringify(application))
    if (resume) {
      data.append('resume', resume)
    }
    if (coverLetter) {
      data.append('coverLetter', coverLetter)
    }
    return this.axios.post('/application', data)
  }

  deleteResume (application) {
    return this.axios.delete('/application/' + application.id + '/resume')
  }

  deleteCoverLetter (application) {
    return this.axios.delete('/application/' + application.id + '/coverletter')
  }

  deleteApplication (application) {
    return this.axios.delete('/application/' + application.id)
  }

  saveResume (application, selectedFile) {
    var data = new FormData()
    if (selectedFile) {
      data.append('resume', selectedFile)
    }
    return this.axios.post('/application/' + application.id + '/resume', data)
  }

  saveCoverLetter (application, selectedFile) {
    var data = new FormData()
    if (selectedFile) {
      data.append('coverletter', selectedFile)
    }
    return this.axios.post('/application/' + application.id + '/coverletter', data)
  }
}

export let applicationService = new ApplicationService()
