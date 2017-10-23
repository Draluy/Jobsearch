<template>
    <div class="login">
        <jobheader/>
        <form>
            <div class="form-group">
                <label for="username">Email</label>
                <input type="email" class="form-control" v-model="username" name="username" id="username"
                       aria-describedby="emailHelp" placeholder="Enter email">
                <small id="emailHelp" class="form-text text-muted">Nous ne partagerons votre email avec personne.
                </small>
            </div>
            <div class="form-group">
                <label for="password">Mot de passe</label>
                <input type="password" class="form-control" v-model="password" name="password" id="password"
                       placeholder="Password">
            </div>
            <button type="submit" @click="login" class="btn btn-primary">Submit</button>
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

</style>
