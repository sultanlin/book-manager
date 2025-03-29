import Book from "@/features/home/Book"
import BookProps from "@/types/BookProps"
import { ReactNode, useEffect, useState } from "react"
import searchBooks from "@/api/search"

function BooksDisplay(): ReactNode {
  // FIX: Layout is ruined with only 1 book, header too big
  // FIX: Some covers are too small, ruins books display
  // TODO: Get BookProps from API
  const [booksSearched, setBooksSearched] = useState<BookProps[]>()
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

  return (
    <main className="content">
      {booksSearched && booksSearched.map((b) => <Book book={b} />)}
    </main>
  )
}

export default BooksDisplay
