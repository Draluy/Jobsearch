<template>
  <div>
    <label>Rendez-vous</label><br/>
    <table class="table table-hover" v-if="application.appointments.length > 0">
      <thead>
      <tr>
        <th scope="col">Date</th>
        <th scope="col">Contact</th>
        <th scope="col"></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="appt in application.appointments">
        <td scope="row">{{appt.date | formatdate}}</td>
        <td>{{appt.contact.firstname}} {{appt.contact.lastname}}</td>
        <td>
          <button type="button" class="btn btn-danger btn-sm" @click="deleteAppointment(appt.id)">Supprimer</button>
        </td>
      </tr>
      </tbody>
    </table>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#appointmentDialog"
            @click="addNewAppointment">Ajouter un rendez-vous
    </button>
    <div class="modal fade" id="appointmentDialog" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
         aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ajouter un rendez-vous</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <appointment :appointment="selectedAppointment"/>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
            <button type="button" class="btn btn-primary" @click="saveNewAppointment">Ajouter</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import store from '../global/Store'
  import AppointmentVue from './Appointment.vue'
  import Appointment from './Appointment'
  import {appointmentService} from './AppointmentService'

  export default {
    name: 'Appointments',
    data () {
      return {
        store: store,
        selectedAppointment: new Appointment()
      }
    },
    props: {
      application: {
        type: Object,
        required: true
      }
    },
    methods: {
      addNewAppointment () {
        this.selectedAppointment = new Appointment()
        let curDate = new Date()
        this.selectedAppointment.date = [curDate.getFullYear(), curDate.getMonth() + 1, curDate.getDate()]
      },
      deleteAppointment (apptId) {
        appointmentService.deleteAppointment(this.application.id, apptId)
          .then(() => {
            store.updateApplication(this.application.id)
          })
      },
      saveNewAppointment () {
        appointmentService.saveAppointment(this.application.id, this.selectedAppointment)
          .then(() => {
            $('#appointmentDialog').modal('hide')
            store.updateApplication(this.application.id)
          })
      }
    },
    components: {
      'appointment': AppointmentVue
    }
  }
</script>

<style scoped>
.table td {
  vertical-align: middle;
}
</style>
