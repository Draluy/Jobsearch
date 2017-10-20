<template>
  <div class="index">
    <jobheader/>
    <navbar selected="appointments"></navbar>
  </div>
</template>

<script>
import NavBar from './global/NavBar.vue'
import Header from './global/Header.vue'

export default {
  name: 'Appointments',
  data () {
    return {
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
