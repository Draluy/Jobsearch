import {companyService} from '../companies/CompanyService'

let store = {
  state: {
    companies: []
  },

  init () {
    companyService.getAllCompanies((companies) => {
      this.state.companies = companies
    })
  },

  setCompanies (companies) {
    this.state.companies = companies
  }
}

export default store
