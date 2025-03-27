import Logo from "@/features/home/Logo"
import Search from "@/features/home/Search"
import UserCard from "@/features/home/UserCard"

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
