import Book from "@/features/books/book"
import { BookMetadata } from "@/types/BookMetadata"
import { ReactNode } from "react"

function BookList({ booksList }: { booksList: BookMetadata[] }): ReactNode {
  // FIX: Add keys to list
  return (
    <main className="content">
      {booksList.map((b) => (
        <Book book={b} />
      ))}
    </main>
  )
}

export default BookList
