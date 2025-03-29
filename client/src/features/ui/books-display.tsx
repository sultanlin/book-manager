import Book from "@/features/ui/book"
import { ReactNode } from "react"
import useBooksSearched from "./useBooksSearched"

function BooksDisplay(): ReactNode {
  // FIX: Layout is ruined with only 1 book, header too big
  // FIX: Some covers are too small, ruins books display

  const booksSearched = useBooksSearched()

  return (
    <main className="content">
      {booksSearched && booksSearched.map((b) => <Book book={b} />)}
    </main>
  )
}

export default BooksDisplay
