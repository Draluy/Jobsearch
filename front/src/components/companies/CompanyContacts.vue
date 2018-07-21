<template>
  <div v-if="contacts && contacts.length > 0">
    <label>Contacts</label>
    <table class="table table-hover">
      <colgroup>
        <col span="1" style="width: 50%;"/>
        <col span="1" style="width: 50%;"/>
      </colgroup>
      <thead>
      <tr>
        <th scope="col">Nom</th>
        <th scope="col">Telephone</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="contact in contacts" :key="contact.id">
        <td>{{contact.lastname}} {{contact.firstname}}</td>
        <td>{{contact.phone}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import store from '../global/Store'

export default {
  name: 'CompanyContacts',
  props: {
    company: {
      type: Object,
      required: true
    }
  },
  data () {
    return {}
  },
  computed: {
    contacts () {
      return store.state.contacts.filter(contact => contact.company.id === this.company.id)
    }
  }
}
</script>

<style scoped>

</style>
