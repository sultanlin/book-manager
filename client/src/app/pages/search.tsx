import BookList from "@/features/books"
import { Logo, UserCard } from "@/features/header"
import useBooksSearched, { SearchBar } from "@/features/search"
import Sidebar from "@/features/sidebar"
import { BookMetadata } from "@/types/BookMetadata"
import { useState } from "react"
import { useSearchParams } from "react-router-dom"

// const POSTS_PER_PAGE = 10
function Search() {
  const [searchParams] = useSearchParams()
  const search = searchParams.get("search") || ""
  console.log(search)
  const [books, setBooks] = useState<BookMetadata[]>([])
  const [loading, setLoading] = useState<boolean>(false)
  // Holy grail, with header, sidebar (nav), and main content
  // Make sidebar disappear/appear feature
  useBooksSearched(setBooks, setLoading, search)

  return (
    <div className="container grid">
      <header className="header">
        <Logo />
        <SearchBar />
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
