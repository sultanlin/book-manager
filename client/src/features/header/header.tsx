import Logo from "@/features/header/logo"
import Search from "@/features/header/search"
import UserCard from "@/features/header/user-card"

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
