import register from "@/features/auth/api/register"
import { useUserStore } from "@/stores/store"
import { AxiosError } from "axios"
import { ActionFunctionArgs, redirect } from "react-router"

export default async function registerAction({ request }: ActionFunctionArgs) {
  const formData = await request.formData()
  // TODO: Make it impossible to use whitespace for username or password
  // Might be easier to force letters, numbers or symbols
  const username = formData.get("username") as string
  const password = formData.get("password") as string
  const passwordConfirmation = formData.get("passwordConfirmation") as string
  const redirectTo = (formData.get("redirectTo") as string) || "/search"

  if (password !== passwordConfirmation) {
    return { error: "Invalid password confirmation" }
  }

  if (username.length < 1 || password.length < 1) {
    return { error: "Username and password must not be empty" }
  }

  const assignToken = useUserStore.getState().assignToken
  const assignUsername = useUserStore.getState().assignUsername

  try {
    const user = await register({
      username: username.toLowerCase(),
      password: password,
      passwordConfirmation: passwordConfirmation,
    })
    assignUsername(user.username)
    assignToken(user.token)

    return redirect(redirectTo)
  } catch (err) {
    if (err instanceof AxiosError) {
      return { error: err.response?.data.message }
    }
    return { error: "Unexpected error has occured" }
  }
}
