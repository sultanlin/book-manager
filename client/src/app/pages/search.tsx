import BooksDisplay from "@/features/books"
import { Logo, UserCard } from "@/features/header"
import useBooksSearched, { SearchBar } from "@/features/search"
import Sidebar from "@/features/sidebar"

function Search() {
  // Holy grail, with header, sidebar (nav), and main content
  // Make sidebar disappear/appear feature
  const booksSearched = useBooksSearched()
  return (
    <div className="container grid">
      {/* <Header /> */}
      <header className="header">
        <Logo />
        <SearchBar />
        <UserCard />
      </header>
      <Sidebar />
      <BooksDisplay booksList={booksSearched} />
    </div>
  )
}

export default Search
