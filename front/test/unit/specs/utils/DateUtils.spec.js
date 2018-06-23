/* eslint no-unused-expressions: 0 */
import DateUtils from '@/utils/DateUtils.js'

describe('DateUtils', () => {
  it('should say that next year is after today', () => {
    const now = new Date()
    const isAfterToday = DateUtils.isAfterToday([now.getFullYear() + 1, now.getMonth(), now.getDate()])
    expect(isAfterToday).to.be.true
  }),

  it('should say that next month is after today', () => {
    const now = DateUtils.toJobSearchDate(new Date())
    now[1] = now[1] + 1 // add a month
    const isAfterToday = DateUtils.isAfterToday(now)
    expect(isAfterToday).to.be.true
  }),

  it('should say that next day is after today', () => {
    const now = DateUtils.toJobSearchDate(new Date())
    now[2] = now[2] + 1 // add a day
    const isAfterToday = DateUtils.isAfterToday(now)
    expect(isAfterToday).to.be.true
  }),

  it('should say that previous day is not after today', () => {
    const now = new Date()
    const isAfterToday = DateUtils.isAfterToday([now.getFullYear(), now.getMonth(), now.getDate() - 1])
    expect(isAfterToday).to.be.false
  }),

  it('should give a negative result if first date is before second date', () => {
    const now = new Date()
    const comparison = DateUtils.compareDates([now.getFullYear(), now.getMonth(), now.getDate()], [now.getFullYear(), now.getMonth(), now.getDate() + 1])
    expect(comparison).to.be.below(0)
  }),

  it('should give a positive result if first date is after second date', () => {
    const now = new Date()
    const comparison = DateUtils.compareDates([now.getFullYear(), now.getMonth(), now.getDate()], [now.getFullYear(), now.getMonth(), now.getDate() - 1])
    expect(comparison).to.be.above(0)
  }),

  it('should return the number of days between two consecutive dates', () => {
    const first = new Date()
    const last = new Date()
    last.setDate(last.getDate() + 12)
    const comparison = DateUtils.daysBetween(DateUtils.toJobSearchDate(first), DateUtils.toJobSearchDate(last))
    expect(comparison).to.equal(12)
  }),

  it('should return the number of days if first date is after the other', () => {
    const first = new Date()
    const last = new Date()
    last.setDate(last.getDate() + 21)
    const comparison = DateUtils.daysBetween(DateUtils.toJobSearchDate(last), DateUtils.toJobSearchDate(first))
    expect(comparison).to.equal(21)
  }),

  it('should convert a jobsearch date to js date correctly', () => {
    let date = DateUtils.toJsDate([2018, 6, 13])
    expect(date.getFullYear()).to.equal(2018)
    expect(date.getMonth() + 1).to.equal(6)
    expect(date.getDate()).to.equal(13)

    date = DateUtils.toJsDate([2018, 6, 23])
    expect(date.getFullYear()).to.equal(2018)
    expect(date.getMonth() + 1).to.equal(6)
    expect(date.getDate()).to.equal(23)
  })

  it('should convert a date to jobsearch date correctly', () => {
    let date = DateUtils.toJsDate([2018, 6, 13])
    let convertedDate = DateUtils.toJobSearchDate(date)
    expect(convertedDate).to.be.an('array').to.deep.equal([2018, 6, 13])
  })
})
