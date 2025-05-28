import { BookMetadata } from "@/types/BookMetadata"

function Book({ book }: { book: BookMetadata }) {
  return (
    <article className="book">
      <img src={book.cover} className="cover" alt="book cover" />
      <p className="name">{book.title}</p>
      <p className="author">{book.author}</p>
    </article>
  )
}

export default Book
