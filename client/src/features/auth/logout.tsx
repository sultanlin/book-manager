import { useUserStore } from "@/stores/store"
import { Logout } from "@mui/icons-material"
import { Link } from "react-router-dom"

function LogoutButton() {
  const logout = useUserStore((state) => state.logout)
  return (
    <Link to={"/login"} onClick={() => logout()} className="logout">
      <Logout />
      logout
    </Link>
  )
}

export default LogoutButton
