import { useUserStore } from "@/stores/store"
import Axios from "axios"

const baseUrl = window.location.origin

export const axiosInstance = Axios.create({
  baseURL: baseUrl,
  headers: {
    "Content-Type": "application/json",
  },
})

axiosInstance.interceptors.request.use(
  (request) => {
    const accessToken = useUserStore.getState().token

    if (accessToken) {
      request.headers["Authorization"] = `Bearer ${accessToken}`
    }
    return request
  },
  (error) => {
    return Promise.reject(error)
  }
)
