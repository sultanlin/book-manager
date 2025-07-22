import { useShelves } from "@/features/shelf/api/get-shelves"
import ShelfCreate from "@/features/shelf/shelf-create"
import ShelfDelete from "@/features/shelf/shelf-delete"
import ShelfRename from "@/features/shelf/shelf-rename"

function ShelvesSettings() {
  const { data, isPending, error } = useShelves()

  if (error)
    return <p className="error">An error has occured: {error.message}</p>
  if (isPending)
    return <p className="pending">Getting shelves, please wait a moment...</p>

  return (
    <div className="settings">
      <h1>Shelf Settings</h1>
      <p className="intro">
        You can manage your shelves here by adding a new shelf and
        renaming/deleting existing shelves.
      </p>
      <ShelfCreate isMaxShelves={data.length >= 8} />
      {data.map((shelf) => (
        <div className="shelf-option">
          <p className="name">{shelf.name}</p>
          <ShelfDelete shelf={shelf} />
          <ShelfRename shelf={shelf} />
        </div>
      ))}
    </div>
  )
}
export default ShelvesSettings
