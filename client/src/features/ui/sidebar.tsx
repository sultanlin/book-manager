import HomeIcon from "@mui/icons-material/Home"
import AddIcon from "@mui/icons-material/Add"
import SidebarButton from "@/features/ui/sidebar-button"

function Sidebar() {
  // FIX: Make sidebar size static (it currently changes based on shelf name size)
  // TODO: Add a burger to minimize navbar, like in youtube
  // Make each setting/button into its own component
  // Add ID for each "collection" and use it as key
  // Max 5 custom shelfs

  const collections = ["Next", "Soon", "Borrowed", "ASAP"]
  return (
    <nav role="toolbar" className="sidebar">
      <SidebarButton isCurrent={true} href="">
        <HomeIcon color="primary" fontSize="large" />
        <h2>Your Books</h2>
      </SidebarButton>
      {collections.map((c) => (
        <SidebarButton href="">
          <HomeIcon color="primary" fontSize="large" />
          <h2>{c}</h2>
        </SidebarButton>
      ))}
      <SidebarButton href="">
        {/* TODO: Plus Icon */}
        <AddIcon color="primary" fontSize="large" />
        <h2>Add Shelf</h2>
      </SidebarButton>
    </nav>
  )
}

export default Sidebar
