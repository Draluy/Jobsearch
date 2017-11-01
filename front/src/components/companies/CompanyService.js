import {RestService} from '../global/RestService'

class CompanyService extends RestService {
  getAllCompanies (setCompanies) {
    return this.axios.get('/company')
      .then(({data}) => {
        setCompanies(data)
      })
      .catch((response) => console.log('error', response))
  }

  saveCompany (company, callbackSuccess, callbackError) {
    return this.axios.post('/company', company)
      .then(callbackSuccess)
      .catch(callbackError)
  }

  deleteCompany (company, callback) {
    return this.axios.delete('/company/' + company.id)
      .then(callback)
      .catch(function (error) {
        if (callback) callback(error)
      })
  }
}

export let companyService = new CompanyService()
