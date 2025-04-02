import BookList from "@/features/books"
import { Logo, UserCard } from "@/features/header"
import useBooksSearched, { SearchBar } from "@/features/search"
import Sidebar from "@/features/sidebar"
import BookMetadata from "@/types/BookProps"
import { useState } from "react"

// const POSTS_PER_PAGE = 10
function Search() {
  const [search, setSearch] = useState("lord+of+the+rings")
  const [books, setBooks] = useState<BookMetadata[]>([])
  const [loading, setLoading] = useState<boolean>(false)
  // Holy grail, with header, sidebar (nav), and main content
  // Make sidebar disappear/appear feature
  useBooksSearched(setBooks, setLoading, search)

  const handleSearchSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    // on submit, we want the search to be set?
    // event.preventDefault()

    const formData = new FormData(event.currentTarget)
    const inputValue = formData.get("search")

    setSearch(inputValue?.toString() || "")
  }
  return (
    <div className="container grid">
      <header className="header">
        <Logo />
        <SearchBar handleSubmit={handleSearchSubmit} />
        <UserCard />
      </header>
      <Sidebar />
      {loading && (
        <div className="content">Getting books, please wait a moment...</div>
      )}
      {!loading && <BookList booksList={books} />}
    </div>
  )
}

export default Search
