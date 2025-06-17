import { ReactNode } from "react"
import { NavLink } from "react-router"

function SidebarButton({
  children,
  href,
  isCurrent,
}: {
  children: ReactNode
  href: string
  isCurrent?: boolean
}) {
  const buttonClass = isCurrent ? "button current" : "button"
  return (
    <a role="button" href={href} className={buttonClass}>
      {children}
    </a>
  )
}

export default SidebarButton
