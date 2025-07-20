import { useSearch } from "@/features/search"
import { BookList } from "@/features/books"
import { useSearchParams } from "react-router"

function Search() {
  const [searchParams] = useSearchParams()
  const bookName = searchParams.get("search") || ""

  const { data, isPending, error } = useSearch(bookName)

  if (isPending)
    return <p className="pending">Getting books, please wait a moment...</p>
  if (error)
    return <p className="error">An error has occured: {error.message}</p>

  return <BookList booksList={data} />
}

export default Search
