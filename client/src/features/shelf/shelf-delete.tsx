import { Shelf } from "@/types/api"
import { useActionState, useRef } from "react"
import { Delete, RotateRight } from "@mui/icons-material"
import { useQueryClient } from "@tanstack/react-query"
import Modal from "@/components/modal"
import { useDeleteShelf } from "./api/delete-shelf"

type ShelfDeleteProps = {
  shelf: Shelf
}

function ShelfDelete({ shelf }: ShelfDeleteProps) {
  const modalRef = useRef<HTMLDialogElement>(null)

  const toggleDialog = () => {
    if (!modalRef.current) return

    if (modalRef.current.hasAttribute("open")) modalRef.current.close()
    else modalRef.current.showModal()
  }

  const queryClient = useQueryClient()
  const deleteShelfMutation = useDeleteShelf(queryClient)

  async function formAction(_: string, formData: FormData): Promise<string> {
    const deleteConfirm = formData.get("delete-confirm")

    if (!deleteConfirm) {
      return "Please press the confirmation button"
    }

    deleteShelfMutation.mutate(
      { shelfId: shelf.id },

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
    <div className="delete">
      <button onClick={toggleDialog}>
        <Delete className="icon" />
        <p>Delete shelf</p>
      </button>
      <Modal toggleDialog={toggleDialog} ref={modalRef}>
        {deleteShelfMutation.isPending ? (
          <div className="pending">
            <RotateRight className="icon" />
            <p>Deleting book, please wait...</p>
          </div>
        ) : (
          <form action={action} className="shelf-delete">
            <fieldset>
              <legend>
                <h2>Are you sure you want to delete shelf ?</h2>
                {formState && <p>*{formState}*</p>}
              </legend>
              <div key={shelf.id}>
                <input
                  type="checkbox"
                  id={"delete" + shelf.id}
                  name="delete-confirm"
                  value="delete-confirm"
                />
                <label htmlFor={"delete" + shelf.id}>
                  I confirm that I want to delete {shelf.name}.
                </label>
              </div>
            </fieldset>
            <button type="submit">Delete shelf</button>
          </form>
        )}
      </Modal>
    </div>
  )
}

export default ShelfDelete
