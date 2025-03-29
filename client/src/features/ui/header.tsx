import Logo from "@/features/ui/logo"
import Search from "@/features/ui/search"
import UserCard from "@/features/ui/user-card"

function Header() {
  return (
    <header className="header">
      <Logo />
      <Search />
      <UserCard />
    </header>
  )
}

export default Header
