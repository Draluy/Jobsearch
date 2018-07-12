<template>
  <div class="row justify-content-center align-items-center screen-height" v-if="store.state.applications.length === 0 && store.state.companies.length === 0">
    <button type="button" class="btn btn-primary btn-lg" @click="createCompany">Ajouter une entreprise</button>
  </div>
</template>
<script>
import store from '../global/Store'

export default {
  name: 'AddCompany',
  data () {
    return {
      store: store
    }
  },
  methods: {
    createCompany () {
      this.$router.push({name: 'companies', params: {creationMode: true}})
    }
  }
}
</script>
<style scoped>
  .screen-height {
    flex-grow: 1;
  }
</style>
