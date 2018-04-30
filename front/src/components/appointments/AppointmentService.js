import {RestService} from '../global/RestService'

class AppointmentService extends RestService {
  getAllAppointmentss (applicationId) {
    return this.axios.get('/application/' + applicationId + '/appointment')
  }

  getAppointment (applicationId, appointmentId) {
    return this.axios.get('/application/' + applicationId + '/appointment/' + appointmentId)
  }

  saveAppointment (applicationId, appointment) {
    return this.axios.post('/application/' + applicationId + '/appointment', appointment)
  }

  deleteAppointment (applicationId, appointmentId) {
    return this.axios.delete('/application/' + applicationId + '/appointment/' + appointmentId)
  }
}

export let appointmentService = new AppointmentService()
