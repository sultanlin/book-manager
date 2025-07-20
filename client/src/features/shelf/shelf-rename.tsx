import { Shelf, ShelfAddRequest } from "@/types/api"
import { useActionState, useRef } from "react"
import { Edit, RotateRight } from "@mui/icons-material"
import { useRenameShelf } from "./api/rename-shelf"
import { useQueryClient } from "@tanstack/react-query"
import Modal from "@/components/modal"

type ShelfRenameProps = {
  shelf: Shelf
}

function ShelfRename({ shelf }: ShelfRenameProps) {
  const modalRef = useRef<HTMLDialogElement>(null)

  const toggleDialog = () => {
    if (!modalRef.current) return

    if (modalRef.current.hasAttribute("open")) modalRef.current.close()
    else modalRef.current.showModal()
  }

  const queryClient = useQueryClient()
  const renameShelfMutation = useRenameShelf(queryClient)

  async function formAction(_: string, formData: FormData): Promise<string> {
    const shelfName = formData.get("shelf-rename") as string
    const shelfAddRequest: ShelfAddRequest = { shelfName: shelfName.trim() }

    if (!shelfName) {
      return "Shelf name missing"
    }

    if (shelfName.length >= 24) {
      return "Name too long"
    }

    renameShelfMutation.mutate(
      { shelfId: shelf.id, shelfAdd: shelfAddRequest },
      {
        onSuccess: () => {
          queryClient.invalidateQueries()
          toggleDialog()
        },
      }
    )
    return ""
  }

  const [formState, action] = useActionState(formAction, "")

  return (
    <div className="rename">
      <button onClick={toggleDialog}>
        <Edit className="icon" />
        <p>Rename shelf</p>
      </button>
      <Modal toggleDialog={toggleDialog} ref={modalRef}>
        {renameShelfMutation.isPending ? (
          <div className="pending">
            <RotateRight className="icon" />
            <p>Renaming book, please wait...</p>
          </div>
        ) : (
          <form action={action} className="shelf-rename">
            <fieldset>
              <legend>
                <h2>Are you sure you want to rename shelf ?</h2>
                {formState && <p>*{formState}*</p>}
              </legend>
              <div key={shelf.id} className="shelf-name">
                <label htmlFor={"rename" + shelf.id}>
                  Shelf name (max 24 characters)
                </label>
                <input
                  type="text"
                  id={"rename" + shelf.id}
                  name="shelf-rename"
                />
              </div>
            </fieldset>
            <button type="submit">Rename shelf</button>
          </form>
        )}
      </Modal>
    </div>
  )
}

export default ShelfRename
