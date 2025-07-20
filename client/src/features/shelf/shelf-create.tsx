import { useActionState, useRef } from "react"
import { Add, RotateRight } from "@mui/icons-material"
import { useQueryClient } from "@tanstack/react-query"
import { useCreateShelf } from "./api/create-shelf"
import { ShelfAddRequest } from "@/types/api"
import Modal from "@/components/modal"

type Props = {
  isMaxShelves: boolean
}

function ShelfCreate({ isMaxShelves }: Props) {
  const modalRef = useRef<HTMLDialogElement>(null)

  const toggleDialog = () => {
    if (!modalRef.current) return

    if (modalRef.current.hasAttribute("open")) modalRef.current.close()
    else modalRef.current.showModal()
  }

  const queryClient = useQueryClient()
  const createShelfMutation = useCreateShelf(queryClient)

  async function formAction(_: string, formData: FormData): Promise<string> {
    const shelfName = formData.get("shelf-add") as string
    console.log("Shelf name is: " + shelfName)

    const shelfAddRequest: ShelfAddRequest = { shelfName: shelfName.trim() }

    if (!shelfName) {
      return "Shelf name missing"
    }

    if (shelfName.length >= 30) {
      return "Name too long"
    }

    createShelfMutation.mutate(
      { shelfAdd: shelfAddRequest },
      {
        onSuccess: () => {
          queryClient.invalidateQueries()
          toggleDialog()
        },
      }
    )
    return shelfName
  }

  const [formState, action] = useActionState(formAction, "")

  return (
    <div className="add">
      <button onClick={toggleDialog} disabled={isMaxShelves}>
        <Add className="icon" />
        <p>Add Shelf</p>
      </button>
      <p>* You can have up to 8 shelves maximum *</p>
      <Modal toggleDialog={toggleDialog} ref={modalRef}>
        {createShelfMutation.isPending ? (
          <div className="pending">
            <RotateRight className="icon" />
            <p>Creating book, please wait...</p>
          </div>
        ) : (
          <form action={action} className="shelf-create">
            <fieldset>
              <legend>
                <h2>Are you sure you want to add a new shelf ?</h2>
                {formState && <p>*{formState}*</p>}
              </legend>
              <div>
                <label htmlFor="shelf-add">
                  Shelf name (max 24 characters)
                </label>
                <input type="text" id="shelf-add" name="shelf-add" />
              </div>
            </fieldset>
            <button type="submit">Add shelf</button>
          </form>
        )}
      </Modal>
    </div>
  )
}

export default ShelfCreate
