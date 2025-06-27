import ShelfView from "./shelf-view"
import { useShelves } from "./api/get-shelves"

function ShelfList() {
  // TODO: set loading screen
  const { data, isLoading, isFetching, isPending, error } = useShelves()

  return (
    <ul className="shelves">
      {data?.map((shelf) => (
        <li key={shelf.id}>
          <ShelfView shelf={shelf} />
        </li>
      ))}
    </ul>
  )
}

export default ShelfList
