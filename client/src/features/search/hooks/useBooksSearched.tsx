import searchBooks from "@/api/search"
import BookProps from "@/types/BookProps"
import { useEffect, useState } from "react"

export default function useBooksSearched() {
  // TODO: Get BookProps from API
  const [booksSearched, setBooksSearched] = useState<BookProps[]>([])
  // const bookName = "harry+potter"
  const bookName = "lord+of+the+rings"
  console.log(searchBooks(bookName))

  useEffect(() => {
    const getBooks = async () => {
      setBooksSearched(await searchBooks(bookName))
    }
    getBooks().catch((err) => console.log(err))
  }, [])

  console.log(booksSearched)
  return booksSearched
}
