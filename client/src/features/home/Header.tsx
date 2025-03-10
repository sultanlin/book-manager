import Logo from "./Logo"
import Search from "./Search"
import User from "./User"

function Header() {
  return (
    <header className="header">
      <Logo />
      <Search />
      <User />
    </header>
  )
}

export default Header
