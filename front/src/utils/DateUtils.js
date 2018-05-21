export default {
  compareDates (date1, date2) {
    let dateAsString1 = getDateAsString(date1)
    let dateAsString2 = getDateAsString(date2)

    return dateAsString1 - dateAsString2
  },

  isAfterToday (date) {
    return new Date(date[0], date[1], date[2]) > new Date()
  }
}

let getDateAsString = function (date) {
  return '' + date[0] + date[1] + date[2]
}
