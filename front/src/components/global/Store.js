import {companyService} from '../companies/CompanyService'
import {contactService} from '../contacts/ContactService'
import {applicationService} from '../applications/ApplicationService'

let store = {
  initialized: false,
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

  getApplication (applicationId) {
    const applications = this.state.applications.filter(app => app.id === applicationId)
    return applications.length > 0 ? applications[0] : null
  },

  init () {
    if (!this.initialized) {
      this.loadCompanies()
      this.loadContacts()
      this.loadApplications()
      this.initialized = true
    }
  }
}

export default store
