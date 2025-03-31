import Book from "@/features/books/book"
import BookProps from "@/types/BookProps"
import { ReactNode } from "react"
// import useBooksSearched from "../useBooksSearched"

function BooksDisplay({ booksList }: { booksList: BookProps[] }): ReactNode {
  // FIX: Layout is ruined with only 1 book, header too big
  // FIX: Some covers are too small, ruins books display

  // const booksSearched = useBooksSearched()

  return (
    <main className="content">
      {booksList.map((b) => (
        <Book book={b} />
      ))}
    </main>
  )
}

export default BooksDisplay
