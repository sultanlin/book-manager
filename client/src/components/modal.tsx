import { ReactNode } from "react"
import { Close } from "@mui/icons-material"

type Props = {
  children: ReactNode
  toggleDialog: () => void
  ref: React.RefObject<HTMLDialogElement | null>
}

function Modal({ children, toggleDialog, ref }: Props) {
  return (
    <dialog
      ref={ref}
      onClick={(e) => {
        if (e.currentTarget === e.target) toggleDialog()
      }}
    >
      <div className="modal">
        {children}
        <button onClick={toggleDialog} className="close">
          <Close className="icon" />
        </button>
      </div>
    </dialog>
  )
}

export default Modal
