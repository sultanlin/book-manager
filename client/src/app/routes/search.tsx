import { useSearch } from "@/features/search"
import { BookList } from "@/features/books"
import { useSearchParams } from "react-router"

function Search() {
  const [searchParams] = useSearchParams()
  const bookName = searchParams.get("search") || ""

  const { data, isLoading, isFetching, isPending, error } = useSearch(bookName)

  if (isLoading) return "Getting books, please wait a moment..." // what is isLoading?!?!?
  if (error) return "An error has occured: " + error.message
  if (isPending) return "Loading..."
  if (isFetching) return "Updating..."

  return <BookList booksList={data} />
}

export default Search
