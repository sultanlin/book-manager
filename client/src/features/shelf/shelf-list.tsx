import ShelfView from "./shelf-view"
import { useShelves } from "./api/getShelves"

function ShelfList() {
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
