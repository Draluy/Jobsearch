import {RestService} from '../global/RestService'

class ContactService extends RestService {
  getAllContacts (setCompanies) {
    return this.axios.get('/contact')
      .then(({data}) => {
        setCompanies(data)
      })
      .catch((response) => console.log('error', response))
  }

  saveContact (contact, callbackSuccess, callbackError) {
    return this.axios.post('/contact', contact)
      .then(callbackSuccess)
      .catch(callbackError)
  }

  deleteContact (contact, callback) {
    return this.axios.delete('/contact/' + contact.id)
      .then(callback)
      .catch(function (error) {
        if (callback) callback(error)
      })
  }
}

export let contactService = new ContactService()
