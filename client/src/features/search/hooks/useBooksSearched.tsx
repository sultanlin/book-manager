import searchBooks from "@/api/search"
import BookProps from "@/types/BookProps"
import { useEffect, useState } from "react"

export default function useBooksSearched(
  setBooks: React.Dispatch<React.SetStateAction<BookMetadata[]>>,
  setLoading: React.Dispatch<React.SetStateAction<boolean>>,
  search: string,
): void {
  // TODO: Get BookProps from API
  const [booksSearched, setBooksSearched] = useState<BookProps[]>([])
  // const bookName = "harry+potter"
  // const bookName = "lord+of+the+rings"
  // console.log(searchBooks(bookName))

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
  }, [])

  console.log(booksSearched)
  return booksSearched
}
