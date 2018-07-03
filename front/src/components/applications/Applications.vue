<template>
  <div class="index">
    <jobheader/>
    <navbar selected="applications"></navbar>
    <div class="row justify-content-center align-items-center screen-height" v-if="store.state.applications.length === 0 && store.state.companies.length === 0">
      <button type="button" class="btn btn-primary btn-lg" @click="createCompany">Ajouter une entreprise</button>
    </div>
    <div v-else style="position: relative">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
            <table class="table table-hover table-bordered">
              <thead>
              <tr>
                <th scope="col">Intitulé</th>
                <th scope="col">Entreprise</th>
                <th scope="col">Créée le</th>
                <th scope="col">Statut</th>
                <th scope="col">Prochain rendez-vous</th>
              </tr>
              </thead>
              <tbody>
              <tr @dblclick="loadApplication(index)" v-for="(appt, index) in store.state.applications" v-bind:key="appt.id">
                <td>{{appt.title}}</td>
                <td>{{appt.company.name}}</td>
                <td>{{appt.date | formatdate}}</td>
                <td>{{appt.status | formatstatut}}</td>
                <td>{{daysBeforeNextAppointment(appt)}}</td>
              </tr>
              </tbody>
            </table>
            <button type="submit" data-toggle="offcanvas" @click="addNewApplication"
                    class="form-control btn btn-primary">Ajouter
            </button>
          </div>
        </div>
      </div>
      <div :class="{'offcanvas': true, 'active': displayApplication}">
        <button type="button" class="close" data-toggle="offcanvas" @click="displayApplication = false"
                aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <application @updateApplication="updateSelectedApplication()"
                     :application="getNewApplication()"
                     @close="displayApplication = false" :action="action"/>
      </div>
    </div>

  </div>
</template>

<script>
import NavBar from '../global/NavBar.vue'
import Header from '../global/Header.vue'
import ApplicationVue from './Application.vue'
import store from '../global/Store'
import Application from './Application'
import DateUtils from '../../utils/DateUtils'

export default {
  name: 'Applications',
  data () {
    return {
      store: store,
      selectedApplicationIndex: undefined,
      displayApplication: false,
      action: 'add'
    }
  },
  components: {
    'navbar': NavBar,
    'jobheader': Header,
    'application': ApplicationVue
  },
  methods: {
    getNewApplication () {
      return this.selectedApplicationIndex !== undefined ? store.state.applications[this.selectedApplicationIndex] : new Application()
    },
    addNewApplication () {
      this.selectedApplicationIndex = undefined
      this.action = 'add'
      this.displayApplication = true
    },
    loadApplication (index) {
      this.selectedApplicationIndex = index
      this.action = 'edit'
      this.displayApplication = true
    },
    updateSelectedApplication () {
      store.updateApplication(store.state.applications[this.selectedApplicationIndex].id)
    },
    daysBeforeNextAppointment (application) {
      if (application.appointments.length === 0) {
        return
      }

      let nextAppointments = application.appointments
        .filter(appt => DateUtils.isTodayOrAfterToday(appt.date))
        .sort(DateUtils.compareDates)

      if (nextAppointments && nextAppointments.length > 0) {
        const now = DateUtils.toJobSearchDate(new Date())
        const nextAppointment = nextAppointments[0]
        const nbDays = DateUtils.daysBetween(now, nextAppointment.date)
        return nbDays === 0 ? 'Aujourd\'hui' : `${nbDays} jours`
      }
    },
    createCompany () {
      this.$router.push({name: 'companies', params: {creationMode: true}})
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  html,
  body {
    overflow-x: hidden; /* Prevent scroll on narrow devices */
    height: 100vh
  }

  .offcanvas {
    position: absolute;
    transition: all .25s ease-out;
    right: -100%;
    top: 0;
    left: 100%;
    overflow: hidden;
    height: 100%;
    background-color: white;
  }

  .offcanvas.active {
    left: 0;
    right: 0;
    height: auto;
  }

  button.close {
    padding: 5px 10px
  }

  button.close span {
    font-size: 2em;
  }

  .screen-height {
    flex-grow: 1;
  }
</style>
