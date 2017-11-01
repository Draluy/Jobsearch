<template>
    <div class="login">
        <jobheader/>
        <form class="form-signin">
          <h2 class="form-signin-heading">S'identifier</h2>
          <label for="inputEmail" class="sr-only">Email</label>
          <input id="inputEmail" v-model="username" class="form-control" placeholder="Email address" required="" autofocus="" type="email">
          <small id="emailHelp" class="form-text text-muted">Nous ne partagerons votre email avec personne.</small>
          <label for="inputPassword" class="sr-only">Mot de passe</label>
          <input id="inputPassword" v-model="password" class="form-control" placeholder="Password" required="" type="password">
          <button class="btn btn-lg btn-primary btn-block mt-3" @click.prevent="login" type="submit">Sign in</button>
        </form>
    </div>
</template>
<script>
  import Header from './global/Header.vue'
  import {loginService} from './global/LoginService'

  export default {
    name: 'Login',
    data () {
      return {
        username: '',
        password: ''
      }
    },
    methods: {
      login () {
        console.log('calling login service')
        loginService.login(this.username, this.password, this.$router)
      }
    },
    components: {
      'jobheader': Header
    }
  }
</script>

<style scoped>
  .form-signin {
    max-width: 330px;
    padding: 15px;
    margin: 0 auto;
  }
</style>
