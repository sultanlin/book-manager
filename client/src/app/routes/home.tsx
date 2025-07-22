import { useSearch } from "@/features/search"
import { BookList } from "@/features/books"

function Home() {
  const { data, isPending, error } = useSearch("")

  if (isPending)
    return <p className="pending">Getting books, please wait a moment...</p>
  if (error)
    return <p className="error">An error has occured: {error.message}</p>

  return <BookList booksList={data} />
}

export default Home
