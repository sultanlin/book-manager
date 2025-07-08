import { BookInfo } from "@/features/books"
import { useBook } from "@/features/books"
import { ShelfDialog } from "@/features/shelf"
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

  if (!book && isPending) return "Retrieving book, please wait a moment..."
  if (!book && error?.status === 404) {
    return (
      <p>
        {bookName} is not in any of your shelves. Please find it again, or
        <Link to={`/search?search=${bookName}`}> click here.</Link>
      </p>
    )
  } else if (!book && error) return "An error has occured: " + error.message

  return (
    <div className="book">
      <section className="book-shelves">
        <ShelfDialog type="add" book={book}>
          <Add className="icon" />
          <p>add to shelf</p>
        </ShelfDialog>
        <ShelfDialog type="remove" book={book}>
          <Delete className="icon" />
          <p>remove from shelf</p>
        </ShelfDialog>
      </section>
      <BookInfo book={book} />
    </div>
  )
}

export default Books
