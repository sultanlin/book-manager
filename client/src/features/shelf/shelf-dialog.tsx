import { Book } from "@/types/api"
import { useShelves } from "./api/get-shelves"
import { ReactNode, useRef } from "react"
import { useBookShelves } from "./api/get-book-shelves"
import { Close, Loop, WarningRounded } from "@mui/icons-material"
import ShelfDialogForm from "./shelf-dialog-form"

type ShelfDialogProps = {
  book: Book
  type: "add" | "remove"
  children: ReactNode
}

function ShelfDialog({ book, type, children }: ShelfDialogProps) {
  const dialogRef = useRef<HTMLDialogElement>(null)

  const triggerDialog = () => {
    if (!dialogRef.current) return

    const currRef = dialogRef.current

    if (currRef.hasAttribute("open")) currRef.close()
    else currRef.showModal()
  }

  const allShelvesQuery = useShelves()
  const bookShelvesQuery = useBookShelves(book.id)

  if (allShelvesQuery.error) return <p>Cannot get your shelves, please retry</p>

  if (allShelvesQuery.isPending || bookShelvesQuery.isPending) {
    return (
      <p>
        <Loop className="icon" />
        Fetching shelf
      </p>
    )
  }

  return (
    <>
      {allShelvesQuery.error ? (
        <button className="trigger" disabled={true}>
          <WarningRounded className="icon" />
          <p>Error getting shelves</p>
        </button>
      ) : (
        <button
          className="trigger"
          onClick={triggerDialog}
          disabled={bookShelvesQuery.isPending || allShelvesQuery.isPending}
        >
          {children}
        </button>
      )}
      <dialog
        ref={dialogRef}
        onClick={(e) => (e.currentTarget === e.target ? triggerDialog() : null)}
      >
        <div className="modal">
          <ShelfDialogForm
            book={book}
            type={type}
            bookShelvesQuery={bookShelvesQuery}
            allShelvesQuery={allShelvesQuery}
            triggerDialog={triggerDialog}
          />
          <button onClick={triggerDialog} className="close">
            <Close className="icon" />
          </button>
        </div>
      </dialog>
    </>
  )
}

export default ShelfDialog
