<template>
  <div>
    <a class="mt-3" id="new-account" href="#" data-toggle="modal" data-target="#create-account">Créer un nouveau
      compte</a>
    <div class="modal fade" id="create-account" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
      <div class="modal-dialog  modal-md" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Création d'un compte</h5>
            <button type="button" ref="closebutton" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body col-12">
            <div v-if="error" class="alert alert-danger" role="alert">
              {{error}}
            </div>
            <form class="form-create-account">
              <label for="inputEmailCreation" class="sr-only">Email</label>
              <input id="inputEmailCreation" v-model="email" class="form-control"
                     placeholder="Adresse mail" required=""
                     autofocus="" type="email">
              <small id="emailHelp" class="form-text text-muted">Nous ne partagerons votre email avec personne.</small>
              <label for="inputPasswordCreation" class="sr-only">Mot de passe</label>
              <input id="inputPasswordCreation" v-model="password" class="form-control"
                     placeholder="Mot de passe" required=""
                     type="password">
              <input id="inputPasswordConfirmation" v-model="passwordVerification" class="form-control"
                     placeholder="Confirmation du mot de passe" required=""
                     type="password">
            </form>
          </div>
          <div class="modal-footer">
            <button class="btn btn-lg btn-primary btn-block mt-3" @click="newAccount" type="submit">Créer un compte
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {loginService} from './LoginService'

export default {
  name: 'account-creation',
  data () {
    return {
      email: '',
      password: '',
      passwordVerification: '',
      error: null,
      showModal: true
    }
  },
  methods: {
    newAccount () {
      loginService.createNewAccount(this.$data, () => {
        this.$refs.closebutton.click()
      }, (error) => {
        if (error.response) {
          this.error = error.response.data
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
