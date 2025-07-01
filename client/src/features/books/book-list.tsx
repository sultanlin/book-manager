import { Book } from "@/types/api"
import BookView from "./book-view"

type BookListProps = {
  booksList: Book[]
}

function BookList({ booksList }: BookListProps) {
  return (
    <div className="books">
      {booksList.map((book) => (
        <BookView book={book} key={book.id} />
      ))}
    </div>
  )
}

export default BookList
