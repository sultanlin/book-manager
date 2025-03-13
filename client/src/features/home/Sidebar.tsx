import HomeIcon from "@mui/icons-material/Home"
import AddIcon from "@mui/icons-material/Add"
import SidebarButton from "./SidebarButton"
function Sidebar() {
  // TODO: Add a burger to minimize navbar, like in youtube
  // Make each setting/button into its own component
  return (
    <div className="sidebar">
      <p className="icon">icon here</p>
      <p>All Books</p>
      <p className="icon">icon here</p>
      <p>Saved Books</p>
      <p className="icon">icon here</p>
      <p>Settings</p>
    </div>
  )
}

export default Sidebar
