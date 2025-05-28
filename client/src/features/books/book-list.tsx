import { BookMetadata } from "@/types/BookMetadata"
import { ReactNode } from "react"
import BookView from "./book-view"

function BookList({ booksList }: { booksList: BookMetadata[] }): ReactNode {
  // FIX: Add keys to list
  return (
    <main className="content">
      {booksList.map((b) => (
        <BookView book={b} />
      ))}
    </main>
  )
}

export default BookList
