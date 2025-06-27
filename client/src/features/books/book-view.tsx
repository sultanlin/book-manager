import { Book } from "@/types/api"
import { Link } from "react-router"

function BookView({ book }: { book: Book }) {
  return (
    <article className="book">
      <Link to={`/books/${book.id}`}>
        <img src={book.cover} className="cover" alt="book cover" />
        <p className="name">{book.title}</p>
        <p className="author">{book.author}</p>
      </Link>
    </article>
  )
}

export default BookView
