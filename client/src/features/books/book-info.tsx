import { Book } from "@/types/api"
import { CalendarToday, Launch, Person, Star } from "@mui/icons-material"

type BooksProps = {
  book: Book
}

// TODO: Change name to "Book"?
function BookInfo({ book }: BooksProps) {
  const bookDate = ` ${book.releaseDate.toLocaleString("default", {
    month: "short",
  })} ${book.releaseDate.getFullYear()}`

  return (
    <section className="book-info">
      <div>
        <img src={book.cover} className="cover" alt="book cover" />
        <div className="metadata">
          <div className="main">
            <h1>{book.title}</h1>
            <h2>{book.author}</h2>
          </div>
          <div className="misc">
            <p className="pages">{book.pages} pages</p>
            {book.rating && (
              <p className="ratings">
                <Star className="icon" /> {book.rating.toFixed(2)}
                <span> | </span>
                <Person className="icon" /> {book.ratingsCount}
              </p>
            )}
            {book.releaseDate && (
              <p className="release-date">
                <CalendarToday className="icon" />
                {bookDate}
              </p>
            )}
            {book.slug && (
              <a href={`https://hardcover.app/books/${book.slug}`}>
                <Launch className="icon" />
                hardcover.app
              </a>
            )}
          </div>
        </div>
      </div>
      <p className="description">{book.description}</p>
    </section>
  )
}

export default BookInfo
