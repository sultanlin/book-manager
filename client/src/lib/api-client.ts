import { useUserStore } from "@/stores/store"
import Axios from "axios"

export const axiosInstance = Axios.create({
  baseURL: "http://192.168.1.44:8080",
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
  },
)
