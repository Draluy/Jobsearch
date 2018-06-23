<template>
  <form class="col-sm-12 col-md-6" @submit="checkForm">
    <div class="form-group">
      <label for="inputDate">Date</label>
      <div class="input-group date" data-provide="datepicker">
        <input type="date" required
               :value="getDate()"
               @input="processDate($event)" class="form-control"
               id="inputDate">
        <div class="input-group-addon">
          <span class="oi oi-calendar" title="chat" aria-hidden="true"></span>
        </div>
      </div>
    </div>
    <div class="form-group">
      <label for="companies">Contact</label>
      <select class="form-control" id="companies" v-model="appointment.contact">
        <option v-for="contact in store.state.contacts" :value="contact" v-bind:key="contact.id">
          {{contact.firstname}} {{contact.lastname}}
        </option>
      </select>
    </div>
  </form>
</template>
<script>
import store from '../global/Store'
import DateUtils from '../../utils/DateUtils'

export default {
  name: 'Appointment',
  data () {
    return {
      store: store
    }
  },
  props: {
    appointment: {
      type: Object,
      required: true
    }
  },
  methods: {
    processDate (event) {
      console.log(event.target.valueAsDate)
      this.appointment.date = DateUtils.toJobSearchDate(event.target.valueAsDate)
    },
    getDate () {
      console.log('this.appointment.date', this.appointment.date)
      console.log('DateUtils.toJsDate(this.appointment.date)', DateUtils.toJsDate(this.appointment.date).getDate())
      return this.appointment.date && this.appointment.date.length > 0 && DateUtils.toJsDate(this.appointment.date).toJSON().slice(0, 10)
    },
    checkForm () {
      console.log('s')
    }
  }
}
</script>

<style scoped>

</style>
