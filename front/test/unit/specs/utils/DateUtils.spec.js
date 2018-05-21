/* eslint no-unused-expressions: 0 */
import DateUtils from '@/utils/DateUtils.js'

describe('DateUtils', () => {
  it('should say that next year is after today', () => {
    const now = new Date()
    const isAfterToday = DateUtils.isAfterToday([now.getFullYear() + 1, now.getMonth(), now.getDate()])
    expect(isAfterToday).to.be.true
  }),

  it('should say that next month is after today', () => {
    const now = new Date()
    const isAfterToday = DateUtils.isAfterToday([now.getFullYear(), now.getMonth() + 1, now.getDate()])
    expect(isAfterToday).to.be.true
  }),

  it('should say that next day is after today', () => {
    const now = new Date()
    const isAfterToday = DateUtils.isAfterToday([now.getFullYear(), now.getMonth(), now.getDate() + 1])
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
  })
})
