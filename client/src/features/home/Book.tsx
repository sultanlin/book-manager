import BookProps from "../../types/BookProps"

function Book({ book }: { book: BookProps }) {
  return (
    <article>
      <p>Name: {book.name}</p>
      <p>Author: {book.author}</p>
      <p>Cover:</p>
      <img
        src={book.cover}
        className="cover"
        alt="everything for dummies book cover"
      />
    </article>
  )
}

export default Book
