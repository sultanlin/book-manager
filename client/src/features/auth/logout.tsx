import { useUserStore } from "@/stores/store"
import { Logout } from "@mui/icons-material"
import { useQueryClient } from "@tanstack/react-query"
import { Link } from "react-router"

function LogoutButton() {
  const queryClient = useQueryClient()
  const logout = useUserStore((state) => state.logout)

  return (
    <Link to={"/login"} onClick={() => logout(queryClient)} className="logout">
      <Logout />
      logout
    </Link>
  )
}

export default LogoutButton
