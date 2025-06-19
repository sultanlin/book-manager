import { Shelf } from "@/types/api"
import { LibraryBooks } from "@mui/icons-material"
import { NavLink } from "react-router"

type ShelfViewProps = {
  shelf: Shelf
}

function ShelfView({ shelf }: ShelfViewProps) {
  // TODO: Set up isCurrent styling
  return (
    <NavLink to={`shelves/${shelf.id}`} className="shelf">
      <LibraryBooks className="icon" />
      <p>{shelf.name}</p>
    </NavLink>
  )
}

export default ShelfView
