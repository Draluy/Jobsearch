<template>
  <div class="login">
    <jobheader/>
    <form class="form-signin">
      <h2 class="form-signin-heading">S'identifier</h2>
      <div v-if="displayLoginError" class="alert alert-danger" role="alert">
        Email ou mot de passe invalide.
      </div>
      <label for="inputEmail" class="sr-only">Email</label>
      <input id="inputEmail" v-model="username" class="form-control" placeholder="Adresse mail" required=""
             autofocus="" type="email">
      <label for="inputPassword" class="sr-only">Mot de passe</label>
      <input id="inputPassword" v-model="password" class="form-control" placeholder="Mot de passe" required=""
             type="password">
      <button class="btn btn-lg btn-primary btn-block mt-3" @click.prevent="login" type="submit">Se connecter</button>
    </form>
    <account-creation></account-creation>
  </div>

</template>
<script>
  import Header from '../global/Header.vue'
  import {loginService} from './LoginService'

  import AccountCreation from './AccountCreation'

  export default {
    name: 'Login',
    data () {
      return {
        username: '',
        password: '',
        displayLoginError: false
      }
    },
    methods: {
      login () {
        loginService.login(this.username, this.password, this.$router, () => {
          this.displayLoginError = true
        })
      }
    },
    components: {
      'account-creation': AccountCreation,
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
