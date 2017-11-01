<template>
    <div class="index">
        <jobheader/>
        <navbar selected="appointments"></navbar>

      <div style="position: relative">
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
            <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
            </tr>
            <tr>
              <th scope="row">1</th>
              <td>Mark</td>
              <td>Otto</td>
              <td>@mdo</td>
            </tr>
            <tr>
              <th scope="row">1</th>
              <td>Mark</td>
              <td>Otto</td>
              <td>@mdo</td>
            </tr>
            </tbody>

        </table>

      <div class="offcanvas">
        <button type="button" class="close" data-toggle="offcanvas" @click="displayAppointment" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>

      </div>
      </div>
      <button class="btn btn-primary" data-toggle="offcanvas" @click="displayAppointment">Ajouter</button>
    </div>
</template>

<script>
  import NavBar from '../global/NavBar.vue'
  import Header from '../global/Header.vue'

  export default {
    name: 'Appointments',
    data () {
      return {}
    },
    components: {
      'navbar': NavBar,
      'jobheader': Header
    },
    methods: {
      displayAppointment () {
        $('.offcanvas').toggleClass('active')
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

  button.close{
    padding: 5px 10px
  }

  button.close span{
    font-size: 2em;
  }
</style>
