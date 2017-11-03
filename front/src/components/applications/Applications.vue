<template>
    <div class="index">
        <jobheader/>
        <navbar selected="applications"></navbar>

        <div style="position: relative">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <table class="table table-hover table-striped table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">Entitulé</th>
                                <th scope="col">Entreprise</th>
                                <th scope="col">Date</th>
                                <th scope="col">Statut</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr @click="selectedApplication = appt" v-for="appt in applications">
                                <td>{{appt.title}}</td>
                            </tr>
                            </tbody>
                        </table>
                        <button type="submit" data-toggle="offcanvas" @click="displayApplication" class="form-control btn btn-primary">Ajouter</button>
                    </div>
                </div>
            </div>
            <div class="offcanvas">
                <button type="button" class="close" data-toggle="offcanvas" @click="displayApplication"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <form>
                    <div class="form-group">
                        <label for="inputName" class="col-form-label">Intitulé</label>
                        <input type="text" required class="form-control" v-model="selectedApplication.title" id="inputName">
                    </div>
                    <div class="form-group">
                        <label for="companies">Entreprise</label>
                        <select required class="form-control" id="companies" v-model="selectedApplication.company">
                            <option :selected="selectedApplication.company && comp.id == selectedApplication.company.id" :value="selectedApplication.company" v-for="comp in store.companies">
                                {{comp.name}}
                            </option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="inputDate">Date</label>
                        <div class="input-group date">
                            <input type="text" v-model="selectedApplication.date" class="form-control" data-date-format="dd/mm/yyyy" data-provide="datepicker" id="inputDate">
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-th"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputStatus">Statut</label>
                        <input type="text" class="form-control" v-model="selectedApplication.statut" id="inputStatus">
                    </div>
                    <div class="form-group row">
                        <div class="form-group col-md-4">
                            <button v-if="selectedApplication.title" @click="deleteCompany" type="submit"
                                    class="form-control btn btn-danger mt-4">🗑 Supprimer
                            </button>
                        </div>
                        <div :class="{'form-group' : true, 'col-md-8': selectedApplication.title, 'col-md-12': !selectedApplication.title}">
                            <button @click="saveApplication" type="submit" class="form-control btn btn-primary mt-4">Sauvegarder
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</template>

<script>
  import NavBar from '../global/NavBar.vue'
  import Header from '../global/Header.vue'
  import Application from './Application'
  import store from '../global/Store'
  import {applicationService} from './ApplicationService'

  export default {
    name: 'Applications',
    data () {
      return {
        applications: [],
        store: store.state,
        selectedApplication: new Application(),
        date: new Date()
      }
    },
    components: {
      'navbar': NavBar,
      'jobheader': Header
    },
    methods: {
      displayApplication () {
        $('.offcanvas').toggleClass('active')
      },
      saveApplication () {
        applicationService.saveApplication(this.selectedApplication)
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
