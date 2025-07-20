import { NavLink, Outlet, useNavigation, useSearchParams } from "react-router"
function AuthLayout() {
  const [searchParams] = useSearchParams()
  const redirectTo = searchParams.get("redirectTo") || "/search"
  const params = redirectTo ? `?redirectTo=${redirectTo}` : ""

  const navigation = useNavigation()
  const isPending = navigation.state === "submitting"
  // TODO: Move pending to button only (set all inputs and button to grey)

  if (isPending)
    return (
      <main className="auth">
        <p className="pending">
          Please wait while logging in/creating account...
        </p>
      </main>
    )

  /* TODO: Add a picture to the left of the form, half the page is pic half is form */
  return (
    <main className="auth">
      <div>
        <nav>
          <NavLink to={"/login" + params}>Sign in</NavLink>
          <NavLink to={"/register" + params}>Sign up</NavLink>
        </nav>
        <Outlet />
      </div>
    </main>
  )
}

export default AuthLayout
