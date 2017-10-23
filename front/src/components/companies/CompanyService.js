import axios from 'axios'
import {RestService} from '../global/RestService'

class CompanyService extends RestService {
  getAllCompanies () {
    axios.get(this.baseUrl + '/company').then((response) => console.log(response))
  }
}

export let companyService = new CompanyService()
