<template>
    <div class="index">
        <jobheader/>
        <navbar selected="companies"></navbar>
        <div class="container-fluid" v-if="companies.length > 0">
            <div class="row">
                <div class="col-4">
                    <table class="table table-hover table-striped table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Nom</th>
                            <th scope="col">Adresse</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr @click="selectedCompany = company" v-for="company in companies">
                            <td>{{company.name}}</td>
                            <td>{{company.address.postalcode}} {{company.address.city}}</td>
                        </tr>
                        </tbody>
                    </table>
                    <button type="submit" class="form-control btn btn-primary" data-toggle="modal" data-target="#addModal">Ajouter</button>
                </div>
                <div class="col-8">
                    <company v-if="selectedCompany" :company="selectedCompany"/>
                </div>
            </div>

        </div>
        <div v-else>
            <div class="mx-auto" style="width: 200px;">
                Pas d'entreprises
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
                        <company :company="getNewCompany()" action="add"/>
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
  import {companyService} from './CompanyService'
  import Company from './Company'

  export default {
    name: 'Companies',
    data () {
      return {
        companies: [],
        selectedCompany: new Company()
      }
    },
    created () {
      companyService.getAllCompanies((result) => {
        this.companies = result
        if (this.companies.length > 0) {
          this.selectedCompany = this.companies[0]
        }
      })
    },
    components: {
      'navbar': NavBar,
      'jobheader': Header,
      'company': CompanyVue
    },
    methods: {
      getNewCompany () {
        return new Company()
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
