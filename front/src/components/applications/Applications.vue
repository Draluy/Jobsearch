<template>
  <div class="index">
    <jobheader/>
    <navbar selected="applications"></navbar>

    <div style="position: relative">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
            <table class="table table-hover table-bordered">
              <thead>
              <tr>
                <th scope="col">Intitulé</th>
                <th scope="col">Entreprise</th>
                <th scope="col">Date</th>
                <th scope="col">Statut</th>
              </tr>
              </thead>
              <tbody>
              <tr @dblclick="loadApplication(appt)" v-for="appt in store.state.applications">
                <td>{{appt.title}}</td>
                <td>{{appt.company.name}}</td>
                <td>{{appt.date | formatdate}}</td>
                <td>{{appt.status}}</td>
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
        <application @updateApplication="updateSelectedApplication()" :application="selectedApplication"
                     @close="displayApplication = false" :action="action"/>
      </div>
    </div>

  </div>
</template>

<script>
  import NavBar from '../global/NavBar.vue'
  import Header from '../global/Header.vue'
  import Application from './Application'
  import ApplicationVue from './Application.vue'
  import store from '../global/Store'

  export default {
    name: 'Applications',
    data () {
      return {
        store: store,
        selectedApplication: new Application(),
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
      addNewApplication () {
        this.selectedApplication = new Application()
        this.action = 'add'
        this.showApplication()
      },
      loadApplication (appt) {
        this.selectedApplication = store.getApplication(appt.id)
        this.action = 'edit'
        this.displayApplication = true
      },
      updateSelectedApplication () {
        store.updateApplication(this.selectedApplication.id)
          .then((app) => {
            this.selectedApplication = app
          })
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
</style>
