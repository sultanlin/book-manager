import { LocalLibraryRounded } from "@mui/icons-material"
import { Link } from "react-router-dom"
function Logo() {
  return (
    <Link to={"/search"} className="logo">
      <LocalLibraryRounded className="icon" />
      <h1>Library</h1>
    </Link>
  )
}

export default Logo
