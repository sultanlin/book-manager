import { Settings } from "@mui/icons-material"
import { Link } from "react-router"
import LogoutButton from "@/features/auth/logout"

function Menu() {
  return (
    <nav>
      <ul className="menu">
        <li className="item">
          <Link to={"settings/shelves"}>
            <Settings />
            Shelf Settings
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
