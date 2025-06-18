import { axiosInstance } from "@/lib/api-client"
import { User } from "@/types/api"
import { LoginCredentials } from "@/types/api"

export default async function login(user: LoginCredentials): Promise<User> {
  const response = await axiosInstance.post<User>("/api/v1/auth/login", user)
  return response.data
}
