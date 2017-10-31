import axios from 'axios'

export class RestService {
  constructor () {
    this.baseUrl = 'http://localhost:8080'
    let axiosInstance = axios.create({
      baseURL: this.baseUrl,
      timeout: 1000
    })
    axiosInstance.defaults.withCredentials = true
    this.axios = axiosInstance
  }
}
