<template>
    <div class="index">
        <jobheader/>
        <navbar selected="companies"></navbar>
        <div class="container-fluid">
            <div class="row">
                <div class="col-4">
                    <table class="table table-hover table-striped table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Nom</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr @click="selectedCompany = company" v-for="company in store.state.companies">
                            <td>{{company.name}}</td>
                        </tr>
                        </tbody>
                    </table>
                    <button type="submit" class="form-control btn btn-primary" data-toggle="modal" data-target="#addModal">Ajouter</button>
                </div>
                <div class="col-8">
                    <company @delete="store.loadCompanies()" @save="store.loadCompanies()" v-if="selectedCompany.name" :company="selectedCompany" action="edit"/>
                </div>
            </div>

        </div>
        <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog  modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ajout d'une entreprise</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body col-12">
                        <company @save="hidePopup" :company="getNewCompany()" action="add"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
  import NavBar from '../global/NavBar.vue'
  import Header from '../global/Header.vue'
  import CompanyVue from './Company.vue'
  import Company from './Company'
  import store from '../global/Store'

  export default {
    name: 'Companies',
    data () {
      return {
        selectedCompany: null,
        store: store
      }
    },
    components: {
      'navbar': NavBar,
      'jobheader': Header,
      'company': CompanyVue
    },
    created () {
      this.selectedCompany = store.state.companies.length > 0 ? store.state.companies[0] : this.getNewCompany()
    },
    methods: {
      getNewCompany () {
        return new Company()
      },
      hidePopup () {
        $('#addModal').modal('hide')
        store.loadCompanies()
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
