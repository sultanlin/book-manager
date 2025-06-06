import { ReactNode } from "react"
import { useFetcher, useSearchParams } from "react-router-dom"

type AuthFormProps = {
  children: ReactNode
  pendingMessage?: string
}
function AuthForm({
  children,
  pendingMessage = "Logging in...",
}: AuthFormProps) {
  const [searchParams] = useSearchParams()
  const redirectTo = searchParams.get("redirectTo")
  // TODO: Remove fetcher usage
  // Handle errors and pending without fetcher?!?
  const fetcher = useFetcher()

  const data = fetcher.data

  const isPending = fetcher.state === "submitting"

  return isPending ? (
    <p>{pendingMessage}</p>
  ) : (
    <fetcher.Form method="post">
      {data?.error && <p className="error">* {data.error} *</p>}
      {children}
      {redirectTo && (
        <input type="hidden" name="redirectTo" value={redirectTo} />
      )}
      <button type="submit">Login</button>
    </fetcher.Form>
  )
}

export default AuthForm
