import ShelfView from "./shelf-view"
import { useShelves } from "./api/get-shelves"

function ShelfList() {
  // TODO: set loading screen
  const { data, isPending, error } = useShelves()

  if (error)
    return <p className="error">An error has occured: {error.message}</p>
  if (isPending)
    return <p className="pending">Getting shelves, please wait a moment...</p>

  if (data.length === 0) {
    return (
      <p className="pending">
        You have no shelf, go to the settings page in the top right to add
        shelves.
      </p>
    )
  }

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
