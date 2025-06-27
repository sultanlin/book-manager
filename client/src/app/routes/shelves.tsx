import BookList from "@/features/books"
import { useShelfBooks } from "@/features/shelf"

type ShelvesProps = {
  params: { shelfId: number }
}

function Shelves({ params }: ShelvesProps) {
  const { data, isPending, error } = useShelfBooks(params.shelfId)

  if (error) return "An error has occured: " + error.message
  if (isPending) return "Getting shelves, please wait a moment..."

  if (!data.length) {
    return <p>This shelf has no books yet</p>
  }

  return <BookList booksList={data} />
}

export default Shelves
