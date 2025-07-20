import { BookInfo } from "@/features/books"
import { useBook } from "@/features/books"
import { ShelfBookModify } from "@/features/shelf"
import { Book } from "@/types/api"
import { Add, Delete } from "@mui/icons-material"
import { Link, useLocation, useSearchParams } from "react-router"

type BooksProps = {
  params: { bookId: number }
}

function Books({ params }: BooksProps) {
  const bookId = params.bookId
  const [searchParams] = useSearchParams()
  const bookName = searchParams.get("bookName")

  const location = useLocation()
  const bookFromParent: Book = location.state.book

  const { data, isPending, error } = useBook(bookId)

  const book = data ? data : bookFromParent

  if (!book && isPending)
    return <p className="pending">Retrieving book, please wait a moment...</p>
  if (!book && error?.status === 404) {
    return (
      <div className="error">
        <p>
          {bookName} is not in any of your shelves. Please find it again, or
          <Link to={`/search?search=${bookName}`}> click here.</Link>
        </p>
      </div>
    )
  } else if (!book && error)
    return <p className="error">An error has occured: {error.message}</p>

  return (
    <div className="book">
      <section className="book-shelves">
        <ShelfBookModify book={book} type={"add"}>
          <Add className="icon" />
          <p>Add to shelf</p>
        </ShelfBookModify>
        <ShelfBookModify book={book} type={"delete"}>
          <Delete className="icon" />
          <p>Delete from shelf</p>
        </ShelfBookModify>
      </section>
      <BookInfo book={book} />
    </div>
  )
}

export default Books
