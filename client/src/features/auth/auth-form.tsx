import { ReactNode } from "react"
import {
  Form,
  useActionData,
  useNavigation,
  useSearchParams,
} from "react-router"

type AuthFormProps = {
  children: ReactNode
  pendingMessage?: string
}

type actionError = {
  error: string
}

function AuthForm({
  children,
  pendingMessage = "Logging in...",
}: AuthFormProps) {
  const [searchParams] = useSearchParams()
  const redirectTo = searchParams.get("redirectTo")
  const data = useActionData() as actionError

  const navigation = useNavigation()
  const isPending = navigation.state === "submitting"

  return isPending ? (
    <p>{pendingMessage}</p>
  ) : (
    <Form method="post">
      {data?.error && <p className="error">* {data.error} *</p>}
      {children}
      {redirectTo && (
        <input type="hidden" name="redirectTo" value={redirectTo} />
      )}
      <button type="submit">Login</button>
    </Form>
  )
}

export default AuthForm
