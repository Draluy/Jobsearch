export default {
  compareDates (date1, date2) {
    let dateAsString1 = getDateAsString(date1)
    let dateAsString2 = getDateAsString(date2)

    return dateAsString1 - dateAsString2
  },

  isAfterToday (date) {
    return this.toJsDate(date) > this.now()
  },

  now: function () {
    let now = new Date()
    now.setHours(0, 0, 0, 0)
    return now
  },

  isTodayOrAfterToday (date) {
    return this.toJsDate(date) >= this.now()
  },

  toJsDate (date) {
    return new Date(Date.UTC(date[0], date[1] - 1, date[2]))
  },

  toJobSearchDate (date) {
    return [date.getFullYear(), date.getMonth() + 1, date.getDate()]
  },

  daysBetween (one, another) {
    const millisecondsInDay = 8.64e7
    console.log('comparaiaons de ', one, another)
    console.log('comparaiaons de ', this.toJsDate(one), this.toJsDate(another))
    return Math.round(Math.abs(this.toJsDate(one) - this.toJsDate(another)) / millisecondsInDay)
  }
}

let getDateAsString = function (date) {
  return '' + date[0] + date[1] + date[2]
}
