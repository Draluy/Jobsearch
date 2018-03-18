<template>
  <div class="login">
    <jobheader/>
    <form class="form-signin">
      <h2 class="form-signin-heading">S'identifier</h2>
      <label for="inputEmail" class="sr-only">Email</label>
      <input id="inputEmail" v-model="username" class="form-control" placeholder="Adresse mail" required=""
             autofocus="" type="email">
      <label for="inputPassword" class="sr-only">Mot de passe</label>
      <input id="inputPassword" v-model="password" class="form-control" placeholder="Mot de passe" required=""
             type="password">
      <button class="btn btn-lg btn-primary btn-block mt-3" @click.prevent="login" type="submit">Se connecter</button>
    </form>
    <a class="mt-3" id="new-account" href="#" data-toggle="modal" data-target="#create-account">Créer un nouveau compte</a>

    <div class="modal fade" id="create-account" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
      <div class="modal-dialog  modal-md" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Création d'un compte</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body col-12">
            <form class="form-create-account">
              <label for="inputEmailCreation" class="sr-only">Email</label>
              <input id="inputEmailCreation" v-model="newAccountData.email" class="form-control" placeholder="Adresse mail" required=""
                     autofocus="" type="email">
              <small id="emailHelp" class="form-text text-muted">Nous ne partagerons votre email avec personne.</small>
              <label for="inputPassword" class="sr-only">Mot de passe</label>
              <input id="inputPasswordCreation" v-model="newAccountData.password" class="form-control" placeholder="Mot de passe" required=""
                     type="password">
              <input id="inputPasswordConfirmation" v-model="newAccountData.passwordConfirmation" class="form-control" placeholder="Confirmation du mot de passe" required=""
                     type="password">
            </form>
          </div>
          <div class="modal-footer">
            <button class="btn btn-lg btn-primary btn-block mt-3" @click="newAccount" type="submit">Créer un compte</button>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>
<script>
  import Header from '../global/Header.vue'
  import {loginService} from './LoginService'
  import {NewAccount} from './newAccountData'

  export default {
    name: 'Login',
    data () {
      return {
        username: '',
        password: '',
        newAccountData: new NewAccount()
      }
    },
    methods: {
      login () {
        console.log('calling login service')
        loginService.login(this.username, this.password, this.$router)
      },
      newAccount () {
        loginService.createNewAccount(this.newAccountData)
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
