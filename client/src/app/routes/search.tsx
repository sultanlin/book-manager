import BookList from "@/features/books"
import useBooksSearched from "@/features/search"
import { Book } from "@/types/api"
import { useState } from "react"
import { useSearchParams } from "react-router-dom"

// const POSTS_PER_PAGE = 10
function Search() {
  const [searchParams] = useSearchParams()
  const search = searchParams.get("search") || ""
  console.log(search)
  const [books, setBooks] = useState<Book[]>([])
  const [loading, setLoading] = useState<boolean>(false)
  useBooksSearched(setBooks, setLoading, search)

  if (loading) {
    return <p>Getting books, please wait a moment...</p>
  }
  return <BookList booksList={books} />
}

export default Search
