import ShelfView from "./shelf-view"
import { useShelves } from "./api/get-shelves"

function ShelfList() {
  // TODO: set loading screen
  const { data, isPending, error } = useShelves()

  if (error)
    return <p className="error">An error has occured: {error.message}</p>
  if (isPending)
    return <p className="pending">Getting shelves, please wait a moment...</p>

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
