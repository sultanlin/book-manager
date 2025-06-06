import { NavLink, Outlet, useSearchParams } from "react-router-dom"
function AuthLayout() {
  const [searchParams] = useSearchParams()
  const redirectTo = searchParams.get("redirectTo") || "/search"
  const params = redirectTo ? `?redirectTo=${redirectTo}` : ""

  return (
    <main className="login">
      <div>
        <nav>
          <NavLink to={"/login" + params}>Sign in</NavLink>
        </nav>
        <Outlet />
      </div>
    </main>
  )
}

export default AuthLayout
