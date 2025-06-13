import { Settings } from "@mui/icons-material"
import { Link } from "react-router-dom"
import LogoutButton from "@/features/auth/logout"

function Menu() {
  return (
    <nav>
      <ul className="menu">
        <li className="item">
          <Link to={"settings"}>
            <Settings />
            Settings
          </Link>
        </li>
        <li className="item">
          <LogoutButton />
        </li>
      </ul>
    </nav>
  )
}

export default Menu
