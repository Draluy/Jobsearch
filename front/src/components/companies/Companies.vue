<template>
    <div class="index">
        <jobheader/>
        <navbar selected="companies"></navbar>

        <table class="table table-hover table-striped table-bordered">
            <thead>
            <tr>
                <th scope="col">Date</th>
                <th scope="col">Société</th>
                <th scope="col">Titre</th>
                <th scope="col">Statut</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="company in companies">
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td>Larry</td>
                <td>the Bird</td>
                <td>@twitter</td>
            </tr>
            </tbody>
        </table>
    </div>
</template>
<script>
  import NavBar from '../global/NavBar.vue'
  import Header from '../global/Header.vue'
  import {companyService} from './CompanyService'

  export default {
    name: 'Companies',
    data () {
      return {}
    },
    computed: {
      companies () {
        return companyService.getAllCompanies()
      }
    },
    components: {
      'navbar': NavBar,
      'jobheader': Header
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
