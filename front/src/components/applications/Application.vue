<template>
  <div class="row justify-content-center">
    <form class="col-sm-12 col-md-6" @submit="checkForm">
      <div class="form-group">
        <label for="inputName" class="col-form-label">Intitulé</label>
        <input type="text" required class="form-control" v-model="application.title" id="inputName">
      </div>
      <div class="form-group">
        <label for="companies">Entreprise</label>
        <select required class="form-control" id="companies" v-model="application.company">
          <option :selected="application.company && comp.id == application.company.id" :value="comp"
                  v-for="comp in store.state.companies">
            {{comp.name}}
          </option>
        </select>
      </div>
      <div class="form-group">
        <label for="inputDate">Date</label>
        <div class="input-group date" data-provide="datepicker">
          <input type="date" v-model="application.date" class="form-control" data-date-format="dd/mm/yyyy"
                 id="inputDate">
          <div class="input-group-addon">
            <span class="oi oi-calendar" title="chat" aria-hidden="true"></span>
          </div>
        </div>
      </div>
      <div class="form-group" v-if="action === 'edit'">
        <label for="inputStatus">Statut</label>
        <select required class="form-control" id="inputStatus" v-model="application.status">
          <option value="ONGOING">En cours</option>
          <option value="CLOSED">Fermee</option>
        </select>
      </div>
      <div class="form-group">
        <label for="inputNotes">Notes</label>
        <textarea id="inputNotes" class="form-control" rows="3"></textarea>
      </div>
      <div class="form-group">
        <div class="form-group custom-file" v-if="!application.resume_file_name">
          <input type="file" @change="processFile($event)" class="custom-file-input" id="resume">
          <label class="custom-file-label" for="resume">CV</label>
        </div>
      </div>
      <div class="form-group">
        <div class="form-group custom-file" v-if="application.resume_file_name">
          <a :download="application.resume_file_name" :href="this.baseUrl+'/application/' + this.application.id + '/resume'">CV</a>
        </div>
      </div>
      <div class="form-group">
        <div class="custom-file">
          <input type="file" class="custom-file-input" id="coverletter">
          <label class="custom-file-label" for="coverletter">Lettre de motivation</label>
        </div>
      </div>
      <div class="form-group row">
        <div class="form-group col-md-4">
          <button v-if="action !== 'add'" @click="deleteApplication" type="submit"
                  class="form-control btn btn-danger mt-4">🗑 Supprimer
          </button>
        </div>
        <div :class="{'form-group' : true, 'col-md-8': action !== 'add', 'col-md-12': action === 'add'}">
          <button type="submit" class="form-control btn btn-primary mt-4">Sauvegarder
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
  import store from '../global/Store'
  import {applicationService} from './ApplicationService'
  import {RestService} from '../global/RestService'

  export default {
    name: 'Application',
    data () {
      return {
        store: store,
        resume: null,
        baseUrl: new RestService().baseUrl
      }
    },
    props: {
      application: {
        type: Object,
        required: true
      },
      action: {
        type: String,
        required: false
      }
    },
    methods: {
      saveApplication () {
        applicationService.saveApplication(this.application, this.resume, () => {
          store.loadApplications()
          this.$emit('close')
        })
      },
      deleteApplication () {
        applicationService.deleteApplication(this.application, () => {
          store.loadApplications()
          this.$emit('close')
        })
      },
      processFile (event) {
        this.resume = event.target.files[0]
      },
      checkForm (e) {
        this.saveApplication()
        e.preventDefault()
      }
    }
  }
</script>

<style>

</style>
