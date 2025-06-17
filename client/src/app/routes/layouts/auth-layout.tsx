import { NavLink, Outlet, useSearchParams } from "react-router"
function AuthLayout() {
  const [searchParams] = useSearchParams()
  const redirectTo = searchParams.get("redirectTo") || "/search"
  const params = redirectTo ? `?redirectTo=${redirectTo}` : ""

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
