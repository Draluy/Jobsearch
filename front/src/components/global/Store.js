import {companyService} from '../companies/CompanyService'
import {contactService} from '../contacts/ContactService'
import {applicationService} from '../applications/ApplicationService'

let store = {
  state: {
    companies: [],
    applications: [],
    contacts: []
  },

  loadContacts () {
    contactService.getAllContacts((contacts) => {
      this.state.contacts = contacts
    })
  },

  loadCompanies () {
    companyService.getAllCompanies((companies) => {
      this.state.companies = companies
    })
  },

  loadApplications () {
    applicationService.getAllApplications((apps) => {
      this.state.applications = apps
    })
  },

  init () {
    this.loadCompanies()
    this.loadContacts()
    this.loadApplications()
  }
}

export default store
