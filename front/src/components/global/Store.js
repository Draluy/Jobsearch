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

  loadContacts: function () {
    let loadContacts = (contacts) => {
      this.state.contacts = contacts
    }
    contactService.getAllContacts(loadContacts.bind(this))
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

  updateApplication (applicationId) {
    let promise = applicationService.getApplication(applicationId)
    promise.then((response) => {
      let application = response.data
      this.state.applications = this.state.applications.map(app => {
        return app.id === applicationId ? application : app
      })
    })

    return promise
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
