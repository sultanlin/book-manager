import { ReactNode } from "react"
import { Form, useActionData, useSearchParams } from "react-router"

type AuthFormProps = {
  children: ReactNode
  pendingMessage?: string
}

type actionError = {
  error: string
}

function AuthForm({ children }: AuthFormProps) {
  const [searchParams] = useSearchParams()
  const redirectTo = searchParams.get("redirectTo")
  const data = useActionData() as actionError

  return (
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
