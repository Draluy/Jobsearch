<template>
    <div class="index">
        <jobheader/>
        <navbar selected="contacts"></navbar>
        <div class="container-fluid">
            <div class="row">
                <div class="col-4">
                    <table class="table table-hover table-striped table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Nom</th>
                            <th scope="col">Prénom</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr @click="selectedContact = contact" v-for="contact in contacts">
                            <td>{{contact.firstname}}</td>
                            <td>{{contact.lastname}}</td>
                        </tr>
                        </tbody>
                    </table>
                    <button type="submit" class="form-control btn btn-primary" data-toggle="modal" data-target="#addModal">Ajouter</button>
                </div>
                <div class="col-8">
                    <contact @delete="loadContacts()" @save="loadContacts()" v-if="selectedContact.lastname" :contact="selectedContact"/>
                </div>
            </div>

        </div>
        <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog  modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ajout d'un contact</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body col-12">
                        <contact @save="hidePopup()" :contact="getNewContact()" action="add"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
  import NavBar from '../global/NavBar.vue'
  import Header from '../global/Header.vue'
  import ContactVue from './Contact.vue'
  import {contactService} from './ContactService'
  import Contact from './Contact'

  export default {
    name: 'Contacts',
    data () {
      return {
        contacts: [],
        selectedContact: new Contact()
      }
    },
    created () {
      this.loadContacts()
    },
    components: {
      'navbar': NavBar,
      'jobheader': Header,
      'contact': ContactVue
    },
    methods: {
      getNewContact () {
        return new Contact()
      },
      hidePopup () {
        $('#addModal').modal('hide')
        this.loadContacts()
      },
      loadContacts () {
        contactService.getAllContacts((result) => {
          this.contacts = result
          if (this.contacts.length > 0) {
            this.selectedContact = this.contacts[0]
          } else {
            this.selectedContact = this.getNewContact()
          }
        })
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
