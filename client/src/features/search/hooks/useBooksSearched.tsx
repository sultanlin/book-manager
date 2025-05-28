import searchBooks from "@/api/search"
import { BookMetadata } from "@/types/BookMetadata"
import { useEffect } from "react"

export default function useBooksSearched(
  setBooks: React.Dispatch<React.SetStateAction<BookMetadata[]>>,
  setLoading: React.Dispatch<React.SetStateAction<boolean>>,
  search: string,
): void {
  useEffect(() => {
    const getBooks = async () => {
      setLoading(true)
      try {
        const data = await searchBooks(search)
        setBooks(data)
        setLoading(false)
      } catch (error) {
        console.log(error)
      }
    }
    getBooks()
    // getBooks().catch((err) => console.log(err))
  }, [search, setLoading, setBooks])
  // TODO: Check if the dependency array is correct
}
