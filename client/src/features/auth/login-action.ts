import login from "@/features/auth/api/login"
import { useUserStore } from "@/stores/store"
import { AxiosError } from "axios"
import { ActionFunctionArgs, redirect } from "react-router"

export default async function loginAction({ request }: ActionFunctionArgs) {
  const formData = await request.formData()
  const username = formData.get("username") as string
  const password = formData.get("password") as string
  const redirectTo = (formData.get("redirectTo") as string) || "/search"

  if (username.length < 1 || password.length < 1) {
    return { error: "Username and password must not be empty" }
  }

  const assignToken = useUserStore.getState().assignToken
  const assignUsername = useUserStore.getState().assignUsername

  try {
    const user = await login({
      username: username.toLowerCase(),
      password: password,
    })
    assignUsername(user.username)
    assignToken(user.token)

    return redirect(redirectTo)
  } catch (err) {
    if (err instanceof AxiosError) {
      if (err.status === 401) {
        return { error: "Invalid username or password" }
      }
      return { error: err.message }
    }
    return { error: "Unexpected error has occured" }
  }
}
