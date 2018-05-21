<template>
    <form>
        <div class="form-group">
            <label for="inputName" class="col-form-label">Nom</label>
            <input type="text" required class="form-control" v-model="contact.lastname" id="inputName">
        </div>
        <div class="form-group">
            <label for="inputAddress">Prénom</label>
            <input type="text" required class="form-control" v-model="contact.firstname" id="inputAddress">
        </div>
        <div class="form-group">
            <label for="companies">Entreprise</label>
            <select required class="form-control" id="companies" v-model="contact.company">
                <option :selected="contact.company && comp.id == contact.company.id" :value="comp" v-for="comp in companies" v-bind:key="comp.id">
                    {{comp.name}} {{company}}
                </option>
            </select>
        </div>
        <div class="form-group">
            <label for="inputPhone">Téléphone</label>
            <input type="text" class="form-control" v-model="contact.phone" id="inputPhone">
        </div>
        <div class="form-group row">
            <div class="form-group col-md-4">
                <button v-if="action!=='add'" @click="deleteContact" type="submit"
                        class="form-control btn btn-danger mt-4">🗑 Supprimer
                </button>
            </div>
            <div :class="{'form-group' : true, 'col-md-8': action!=='add', 'col-md-12': action=='add'}">
                <button @click="saveContact" type="submit" class="form-control btn btn-primary mt-4">Sauvegarder
                </button>
            </div>
        </div>
    </form>
</template>
<script>
import {contactService} from './ContactService'
import {companyService} from '../companies/CompanyService'

export default {
  name: 'Contact',
  props: {
    contact: {
      type: Object,
      required: false
    },
    action: {
      type: String,
      required: false
    },
    company: {
      type: Object,
      required: false
    }
  },
  data () {
    return {
      companies: []
    }
  },
  created () {
    companyService.getAllCompanies((companies) => {
      this.companies = companies
    })
  },
  methods: {
    saveContact () {
      contactService.saveContact(this.contact, (response) => {
        this.$emit('save')
      })
    },
    deleteContact () {
      contactService.deleteContact(this.contact, (response) => {
        this.$emit('delete')
      })
    }
  }
}
</script>

<style scoped>

</style>
