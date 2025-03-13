import Logo from "./Logo"
import Search from "./Search"
import UserCard from "./UserCard"

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
