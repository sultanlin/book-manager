import Book from "@src/features/home/Book"
import BookProps from "@src/types/BookProps"
import { ReactNode } from "react"
import img from "../../assets/cover.jpg"

function BooksDisplay(): ReactNode {
  // TODO: Layout is ruined with only 1 book
  const books: BookProps[] = [
    { name: "first book", cover: img, author: "J.K. 1" },
    { name: "second book", cover: img, author: "J.K. 2" },
    { name: "first book", cover: img, author: "J.K. 1" },
    { name: "first book", cover: img, author: "J.K. 1" },
    { name: "first book", cover: img, author: "J.K. 1" },
    { name: "first book", cover: img, author: "J.K. 1" },
    { name: "first book", cover: img, author: "J.K. 1" },
    { name: "first book", cover: img, author: "J.K. 1" },
    { name: "first book", cover: img, author: "J.K. 1" },
    { name: "first book", cover: img, author: "J.K. 1" },
    { name: "first book", cover: img, author: "J.K. 1" },
    { name: "second book", cover: img, author: "J.K. 2" },
    { name: "second book", cover: img, author: "J.K. 2" },
    { name: "second book", cover: img, author: "J.K. 2" },
    { name: "second book", cover: img, author: "J.K. 2" },
    { name: "second book", cover: img, author: "J.K. 2" },
    { name: "second book", cover: img, author: "J.K. 2" },
    { name: "second book", cover: img, author: "J.K. 2" },
    { name: "second book", cover: img, author: "J.K. 2" },
    { name: "second book", cover: img, author: "J.K. 2" },
  ]
  return (
    <main className="content">
      {books.map((b) => (
        <Book book={b} />
      ))}
    </main>
  )
}

export default BooksDisplay
