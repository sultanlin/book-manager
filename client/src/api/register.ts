import { axiosInstance } from "@/lib/api-client"
import { User } from "@/types/api"
import { SignupCredentials } from "@/types/api"

export default async function register(user: SignupCredentials): Promise<User> {
  const response = await axiosInstance.post<User>("/api/v1/auth/register", user)
  return response.data
}
