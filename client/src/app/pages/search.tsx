import BooksDisplay from "@/features/books"
import Header from "@/features/header"
import useBooksSearched from "@/features/search"
import Sidebar from "@/features/sidebar"

function Search() {
  // Holy grail, with header, sidebar (nav), and main content
  // Make sidebar disappear/appear feature
  const booksSearched = useBooksSearched()
  return (
    <div className="container grid">
      <Header />
      <Sidebar />
      <BooksDisplay booksList={booksSearched} />
    </div>
  )
}

export default Search
