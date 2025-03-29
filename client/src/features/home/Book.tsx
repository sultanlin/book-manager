import BookProps from "@/types/BookProps"

function Book({ book }: { book: BookProps }) {
  return (
    <article className="book">
      <img
        src={book.cover}
        className="cover"
        alt="everything for dummies book cover"
      />
      <p>{book.name}</p>
      <p>{book.author}</p>
    </article>
  )
}

export default Book
