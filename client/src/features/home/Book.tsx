import BookProps from "../../types/BookProps"

function Book({ book }: { book: BookProps }) {
  return (
    <article>
      <h1>Books</h1>
      <p>Book name: {book.name}</p>
      <p>Book cover:</p>
      <img
        src={book.cover}
        className="cover"
        alt="everything for dummies book cover"
      />
    </article>
  )
}

export default Book
