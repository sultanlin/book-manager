import AccountCircleIcon from "@mui/icons-material/AccountCircle"
import { useEffect, useRef, useState } from "react"
import Menu from "./menu"

function UserCard() {
  const [open, setOpen] = useState(false)

  const dropdownRef = useRef<HTMLDivElement>(null)

  const triggerMenu = () => {
    setOpen(!open)
  }

  useEffect(() => {
    const handler = (e: MouseEvent) => {
      if (
        dropdownRef.current &&
        !dropdownRef.current.contains(e.target as Node)
      ) {
        setOpen(false)
      }
    }
    document.addEventListener("click", handler)

    return () => {
      document.removeEventListener("click", handler)
    }
  }, [dropdownRef])

  return (
    <div className="usercard" ref={dropdownRef}>
      <button onClick={triggerMenu}>
        <AccountCircleIcon className="icon" />
      </button>
      {open && <Menu />}
    </div>
  )
}

export default UserCard
