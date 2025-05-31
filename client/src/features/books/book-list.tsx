import { BookMetadata } from "@/types/BookMetadata"
import { ReactNode } from "react"
import BookView from "./book-view"

function BookList({ booksList }: { booksList: BookMetadata[] }): ReactNode {
  // FIX: Add keys to list
  return booksList.map((b) => <BookView book={b} />)
}

export default BookList
