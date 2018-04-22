<template>
    <ul class="nav nav-tabs">
     <li class="nav-item">
      <router-link :class="['nav-link', selected === 'applications'? 'active' : '']" to="applications">Candidatures</router-link>
     </li>
        <li class="nav-item">
      <router-link :class="['nav-link', selected === 'companies'? 'active' : '']" to="companies">Entreprises</router-link>
     </li>
     <li class="nav-item">
      <router-link :class="['nav-link', selected === 'contacts'? 'active' : '']" to="contacts">Contacts</router-link>
     </li>
    </ul>
</template>

<script>
  export default {
    name: 'NavBar',
    props: ['selected'],
    data () {
      return {
      }
    }
  }
</script>

<style scoped>

</style>
