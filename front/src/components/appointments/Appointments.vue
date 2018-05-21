<template>
  <div>
    <label>Rendez-vous</label><br/>
    <table class="table table-hover " v-if="application.appointments && application.appointments.length > 0">
      <colgroup>
        <col span="1" style="width: 25%;"/>
        <col span="1" style="width: 50%;"/>
        <col span="1" style="width: 25%"/>
      </colgroup>
      <thead>
      <tr>
        <th scope="col">Date</th>
        <th scope="col">Contact</th>
        <th scope="col"></th>
      </tr>
      </thead>
      <tbody>
      <tr v-if="application.appointments" v-for="appt in application.appointments" v-bind:key="appt.id">
        <td scope="row">{{appt.date | formatdate}}</td>
        <td>{{appt.contact.firstname}} {{appt.contact.lastname}}</td>
        <td style="text-align: right">
          <button type="button" class="btn btn-outline-danger btn-sm" @click="deleteAppointment(appt.id)">Supprimer
          </button>
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
            <button type="button" class="btn btn-primary" @click="saveAppointment">Ajouter</button>
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
    saveAppointment () {
      if (this.application.id) {
        // update of an application
        appointmentService.saveAppointment(this.application.id, this.selectedAppointment)
          .then(() => {
            store.updateApplication(this.application.id)
            $('#appointmentDialog').modal('hide')
          })
      } else {
        // creation of an application, just update the model, the appointment will be saved when saving the application
        let appointments = this.application.appointments
        appointments.push(this.selectedAppointment)
        this.application = Object.assign({}, this.application, {appointments: appointments})
        $('#appointmentDialog').modal('hide')
      }
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
