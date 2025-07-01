import AccountCircleIcon from "@mui/icons-material/AccountCircle"
import { useRef } from "react"
import Menu from "./menu"

function UserCard() {
  const menuRef = useRef<HTMLDialogElement>(null)

  const triggerMenu = () => {
    if (!menuRef.current) return

    const currRef = menuRef.current

    if (currRef.hasAttribute("open")) currRef.close()
    else currRef.showModal()
  }

  return (
    <div className="usercard">
      <button onClick={triggerMenu}>
        <AccountCircleIcon className="icon" />
      </button>
      <dialog
        ref={menuRef}
        onClick={(e) => (e.currentTarget === e.target ? triggerMenu() : null)}
      >
        <Menu />
      </dialog>
    </div>
  )
}

export default UserCard
