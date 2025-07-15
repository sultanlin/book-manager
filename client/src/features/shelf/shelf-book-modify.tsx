import { Book, Shelf } from "@/types/api"
import { ReactNode, useActionState, useRef } from "react"
import { useShelves } from "./api/get-shelves"
import { useBookShelves } from "./api/get-book-shelves"
import { Loop, WarningRounded } from "@mui/icons-material"
import Modal from "@/components/modal"
import { useQueryClient } from "@tanstack/react-query"
import { useAddBookToShelf } from "./api/add-book"
import { useDeleteBookFromShelf } from "./api/delete-book"

type Props = {
  children: ReactNode
  book: Book
  type: "add" | "delete"
}

// TODO: Review user experience
function ShelfBookModify({ children, book, type }: Props) {
  const queryClient = useQueryClient()
  const addMutation = useAddBookToShelf(queryClient)
  const deleteBookFromShelf = useDeleteBookFromShelf(queryClient)

  const dialogRef = useRef<HTMLDialogElement>(null)

  const toggleDialog = () => {
    if (!dialogRef.current) return

    if (dialogRef.current.hasAttribute("open")) dialogRef.current.close()
    else dialogRef.current.showModal()
  }

  async function formAction(_: string, formData: FormData): Promise<string> {
    const shelves = formData.getAll("shelves") as string[]

    if (shelves.length <= 0) {
      return " (Please select a shelf)"
    }

    if (type === "add") {
      const popped = shelves.pop()
      if (popped === undefined) {
        return " (No shelf selected)"
      }

      addMutation.mutate(
        { shelfId: parseInt(popped), book: book },
        {
          onSuccess: () => {
            shelves.forEach((shelfId: string) =>
              addMutation.mutate({ shelfId: parseInt(shelfId), book: book })
            )
            queryClient.invalidateQueries()
            toggleDialog()
          },
        }
      )
    } else {
      shelves.forEach((shelfId: string) =>
        deleteBookFromShelf.mutate(
          { shelfId: parseInt(shelfId), bookId: book.id },
          {
            onSuccess: () => {
              queryClient.invalidateQueries()
              toggleDialog()
            },
          }
        )
      )
    }

    return ""
  }

  const [formState, action, isPendingForm] = useActionState(formAction, "")

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

  const disableCheckbox = (currentShelf: Shelf): boolean => {
    if (!bookShelvesQuery.data || bookShelvesQuery.error) {
      return type === "add" ? false : true
    }
    const allShelvesId = bookShelvesQuery.data?.map((bookShelf) => bookShelf.id)
    return type === "add"
      ? allShelvesId.includes(currentShelf.id)
      : !allShelvesId.includes(currentShelf.id)
  }

  return (
    <div>
      {allShelvesQuery.error ? (
        <button className="trigger" disabled={true}>
          <WarningRounded className="icon" />
          <p>Error getting shelves</p>
        </button>
      ) : (
        <button
          className="trigger"
          onClick={toggleDialog}
          disabled={bookShelvesQuery.isPending || allShelvesQuery.isPending}
        >
          {children}
        </button>
      )}
      <Modal toggleDialog={toggleDialog} ref={dialogRef}>
        <form action={action}>
          <fieldset>
            <legend>
              Which shelf do you want to
              {type === "add" ? " add this book to" : " delete this book from"}?
              {formState}
            </legend>
            {allShelvesQuery.data?.map((shelf) => {
              const shelfIdString = "" + shelf.id
              return (
                <div key={shelf.id} className="checkboxes">
                  {disableCheckbox(shelf) ? (
                    <input type="checkbox" disabled={true} />
                  ) : (
                    <input
                      type="checkbox"
                      id={shelfIdString}
                      name="shelves"
                      value={shelfIdString}
                    />
                  )}
                  <label htmlFor={shelfIdString}>{shelf.name}</label>
                </div>
              )
            })}
          </fieldset>
          <button type="submit" disabled={isPendingForm}>
            {type === "add" ? "Add" : "Delete"}
          </button>
        </form>
      </Modal>
    </div>
  )
}

export default ShelfBookModify
