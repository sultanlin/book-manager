import { Book } from "@/types/api"

function BookView({ book }: { book: Book }) {
  return (
    <article className="book">
      <img src={book.cover} className="cover" alt="book cover" />
      <p className="name">{book.title}</p>
      <p className="author">{book.author}</p>
    </article>
  )
}

export default BookView
