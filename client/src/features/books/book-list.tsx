import { Book } from "@/types/api"
import { ReactNode } from "react"
import BookView from "./book-view"

function BookList({ booksList }: { booksList: Book[] }): ReactNode {
  // FIX: Add keys to list
  return booksList.map((b) => <BookView book={b} />)
}

export default BookList
