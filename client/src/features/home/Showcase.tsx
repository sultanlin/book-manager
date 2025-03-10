import Book from "./Book"
import BookProps from "../../types/BookProps"
import { ReactNode } from "react"
import img from "../../assets/cover.jpg"

function Showcase(): ReactNode {
  const books: BookProps[] = [
    { name: "first book", cover: img },
    { name: "second book", cover: img },
  ]
  return (
    <main className="content">
      {books.map((b) => (
        <Book book={b} />
      ))}
    </main>
  )
}

export default Showcase
