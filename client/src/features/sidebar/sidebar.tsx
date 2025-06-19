import ShelfList from "@/features/shelf"

function Sidebar() {
  // TODO: Add a burger to minimize navbar, like in youtube
  // Make each setting/button into its own component
  return (
    <section className="sidebar">
      <nav>
        <ShelfList />
      </nav>
    </section>
  )
}

export default Sidebar
