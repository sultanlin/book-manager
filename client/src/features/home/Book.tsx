import BookProps from "@/types/BookProps"

function Book({ book }: { book: BookProps }) {
  return (
    <article className="book">
      <img
        src={book.cover}
        className="cover"
        alt="everything for dummies book cover"
      />
      <p>Name: {book.name}</p>
      <p>Author: {book.author}</p>
    </article>
  )
}

export default Book
