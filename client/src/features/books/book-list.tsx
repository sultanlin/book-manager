import Book from "@/features/books/book"
import BookMetadata from "@/types/BookProps"
import { ReactNode } from "react"

function BookList({ booksList }: { booksList: BookMetadata[] }): ReactNode {
  return (
    <main className="content">
      {booksList.map((b) => (
        <Book book={b} />
      ))}
    </main>
  )
}

export default BookList
