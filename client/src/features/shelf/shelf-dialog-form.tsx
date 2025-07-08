import { Book, Shelf } from "@/types/api"
import { useActionState, useRef } from "react"
import { axiosInstance } from "@/lib/api-client"
import {
  useMutation,
  useQueryClient,
  UseQueryResult,
} from "@tanstack/react-query"
import { AxiosError } from "axios"

type ShelfDialogFormProps = {
  book: Book
  type: "add" | "remove"
  bookShelvesQuery: UseQueryResult<Shelf[], AxiosError>
  allShelvesQuery: UseQueryResult<Shelf[], AxiosError>
  triggerDialog: () => void
}

const disableCheckbox = (
  currentShelf: Shelf,
  // bookShelves: Shelf[] | undefined,
  bookShelvesQuery: UseQueryResult<Shelf[], AxiosError>,
  type: "add" | "remove"
): boolean => {
  // console.log(bookShelves)

  if (!bookShelvesQuery.data || bookShelvesQuery.error) {
    return type === "add" ? false : true
  }
  const allShelvesId = bookShelvesQuery.data?.map((bookShelf) => bookShelf.id)
  return type === "add"
    ? allShelvesId.includes(currentShelf.id)
    : !allShelvesId.includes(currentShelf.id)
}

const actionType = {
  add: {
    message: "add this book to",
    mutation: async ({
      shelfId,
      bookObj,
    }: {
      shelfId: number
      bookObj: Book
    }) => {
      const resp = await axiosInstance.post(
        `/api/v1/shelves/${shelfId}/books`,
        bookObj
      )
      return resp.data
    },
  },
  remove: {
    message: "remove this book from",
    mutation: async ({
      shelfId,
      bookId,
    }: {
      shelfId: number
      bookId: number
    }) => {
      const resp = await axiosInstance.delete(
        `/api/v1/shelves/${shelfId}/books/${bookId}`
      )
      return resp.data
    },
  },
}

function ShelfDialogForm({
  book,
  type,
  allShelvesQuery,
  bookShelvesQuery,
  triggerDialog,
}: ShelfDialogFormProps) {
  const queryClient = useQueryClient()
  const addMutation = useMutation({
    mutationFn: actionType.add.mutation,
    onSuccess: () => queryClient.invalidateQueries(),
  })
  const removeMutation = useMutation({
    mutationFn: actionType.remove.mutation,
    onSuccess: () => queryClient.invalidateQueries(),
  })

  const formRef = useRef<HTMLFormElement>(null)

  async function myaction(_: string, formData: FormData): Promise<string> {
    const shelves = formData.getAll("shelves") as string[]

    if (shelves.length === 0) {
      return " (Please select a shelf)"
    }

    if (type === "add") {
      const popped = shelves.pop()
      if (popped === undefined) {
        return " (No shelf selected)"
      }
      addMutation.mutate(
        { shelfId: parseInt(popped), bookObj: book },
        {
          onSuccess: () => {
            shelves.forEach((shelfId: string) =>
              addMutation.mutate({ shelfId: parseInt(shelfId), bookObj: book })
            )
            queryClient.invalidateQueries()
            triggerDialog()
          },
        }
      )
    } else {
      shelves.forEach((shelfId: string) =>
        removeMutation.mutate(
          { shelfId: parseInt(shelfId), bookId: book.id },
          {
            onSuccess: () => {
              queryClient.invalidateQueries()
              triggerDialog()
            },
          }
        )
      )
    }
    return ""
  }

  const [formState, formAction, isPendingForm] = useActionState(myaction, "")

  return (
    <form action={formAction} ref={formRef}>
      <fieldset>
        <legend>
          Which shelf do you want to{" "}
          {type === "add" ? actionType.add.message : actionType.remove.message}?
          {formState}
        </legend>
        {allShelvesQuery.data?.map((shelf) => {
          const isDisabled = disableCheckbox(shelf, bookShelvesQuery, type)
          const shelfIdString = "" + shelf.id
          return (
            <div key={shelf.id} className="checkboxes">
              <input
                type="checkbox"
                id={!isDisabled ? shelfIdString : ""}
                name="shelves"
                value={shelfIdString}
                disabled={isDisabled || isPendingForm}
              />
              <label htmlFor={shelfIdString}>{shelf.name}</label>
            </div>
          )
        })}
      </fieldset>
      <button type="submit" disabled={isPendingForm}>
        {type === "add" ? "Add to shelves" : "Remove from shelves"}
      </button>
    </form>
  )
}

export default ShelfDialogForm
