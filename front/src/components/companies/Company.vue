<template>
  <form>
    <div class="form-group">
      <label for="inputName" class="col-form-label">Nom</label>
      <input type="text" required class="form-control" v-model="company.name" id="inputName">
    </div>
    <div class="form-group">
      <label for="inputAddress">Addresse</label>
      <input type="text" required class="form-control" v-model="company.address.address" id="inputAddress">
    </div>
    <div class="form-group row mb-0">
      <div class="form-group col-md-8">
        <label for="inputCity">Ville</label>
        <input type="text" class="form-control" v-model="company.address.city" id="inputCity">
      </div>
      <div class="form-group col-md-4">
        <label for="inputZip">Code Postal</label>
        <input type="text" class="form-control" v-model="company.address.postalcode" id="inputZip">
      </div>
    </div>
    <div class="form-group">
      <label for="inputWebsite">Site web</label>
      <input type="text" class="form-control" v-model="company.website" id="inputWebsite">
    </div>
    <div class="form-group">
      <label for="inputPhone">Téléphone</label>
      <input type="text" class="form-control" v-model="company.phone" id="inputPhone">
    </div>
    <div class="form-group" v-if="action !== 'add'">
      <companycontacts :company="company"/>
    </div>
    <div class="form-group row">
      <div class="form-group col-md-4">
        <button v-if="canDeleteApplicationSafely" @click="deleteCompany" type="submit"
                class="form-control btn btn-danger mt-4">🗑 Supprimer
        </button>
      </div>
      <div :class="{'form-group' : true, 'col-md-8': canDeleteApplicationSafely, 'col-md-12': !canDeleteApplicationSafely}">
        <button @click="saveCompany" type="submit" class="form-control btn btn-primary mt-4">Sauvegarder
        </button>
      </div>
    </div>
  </form>
</template>
<script>
import {companyService} from './CompanyService'
import CompanyContacts from './CompanyContacts.vue'
import store from '../global/Store'

export default {
  name: 'Company',
  props: {
    company: {
      type: Object,
      required: true
    },
    action: {
      type: String,
      required: true
    }
  },
  data () {
    return {
      store: store
    }
  },
  computed: {
    canDeleteApplicationSafely () {
      let applicationsUsingThisCompany = this.store.state.applications
        .filter(application => application.company.id === this.company.id)
      let contactsOfThisCompany = this.store.state.contacts
        .filter(contact => contact.company.id === this.company.id)
      return !applicationsUsingThisCompany.length && !contactsOfThisCompany.length && this.action !== 'add'
    }
  },
  methods: {
    saveCompany () {
      companyService.saveCompany(this.company, (response) => {
        this.$emit('save')
      })
    },
    deleteCompany () {
      companyService.deleteCompany(this.company, (response) => {
        this.$emit('delete')
      })
    }
  },
  components: {
    'companycontacts': CompanyContacts
  }
}
</script>

<style scoped>

</style>
