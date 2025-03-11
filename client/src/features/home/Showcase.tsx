import Book from "./Book"
import BookProps from "../../types/BookProps"
import { ReactNode } from "react"
import img from "../../assets/cover.jpg"

function Showcase(): ReactNode {
  const books: BookProps[] = [
    { name: "first book", cover: img, author: "J.K. 1" },
    { name: "second book", cover: img, author: "J.K. 2" },
  ]
  return (
    <main className="content">
      <h1>Books</h1>
      {books.map((b) => (
        <Book book={b} />
      ))}
    </main>
  )
}

export default Showcase
