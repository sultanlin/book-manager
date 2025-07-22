import { BookList } from "@/features/books"
import { useShelfBooks } from "@/features/shelf"

type ShelvesProps = {
  params: { shelfId: number }
}

function Shelves({ params }: ShelvesProps) {
  const { data, isPending, error } = useShelfBooks(params.shelfId)

  if (isPending)
    return <p className="pending">Getting shelves, please wait a moment...</p>
  if (error)
    return <p className="error">An error has occured: {error.message}</p>

  if (!data.length) {
    return (
      <p className="pending">
        This shelf has no books yet, you can add books by searching for it and
        clicking the "add to shelf" book
      </p>
    )
  }

  return <BookList booksList={data} />
}

export default Shelves
