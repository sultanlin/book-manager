import searchBooks from "@/api/search"
import BookList from "@/features/books"
import { useQuery } from "@tanstack/react-query"
import { useSearchParams } from "react-router-dom"

function Search() {
  const [searchParams] = useSearchParams()
  const bookName = searchParams.get("search") || ""

  const { data, isLoading, isFetching, isPending, error } = useQuery({
    queryKey: ["search", bookName],
    queryFn: () => searchBooks(bookName),
  })

  if (isLoading) return "Getting books, please wait a moment..." // what is isLoading?!?!?
  if (error) return "An error has occured: " + error.message
  if (isPending) return "Loading..."
  if (isFetching) return "Updating..."

  return <BookList booksList={data} />
}

export default Search
