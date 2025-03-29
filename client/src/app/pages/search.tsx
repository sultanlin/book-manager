import { BooksDisplay, Header, Sidebar } from "@/features/home"

function Search() {
  // Holy grail, with header, sidebar (nav), and main content
  // Make sidebar disappear/appear feature
  return (
    <div className="container grid">
      <Header />
      <Sidebar />
      <BooksDisplay />
    </div>
  )
}

export default Search
