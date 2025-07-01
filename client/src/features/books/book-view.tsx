import { Book } from "@/types/api"
import { Link } from "react-router"

function BookView({ book }: { book: Book }) {
  return (
    <article className="book-card">
      <Link to={`/books/${book.id}`} state={{ book: book }}>
        <img src={book.cover} className="cover" alt="book cover" />
        <p className="name">{book.title}</p>
        <p className="author">{book.author}</p>
      </Link>
    </article>
  )
}

export default BookView
