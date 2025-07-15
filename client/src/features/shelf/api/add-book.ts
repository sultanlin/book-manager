import { axiosInstance } from "@/lib/api-client"
import { Book } from "@/types/api"
import { QueryClient, useMutation } from "@tanstack/react-query"

export async function addBookToShelf(
  book: Book,
  shelfId: number
): Promise<Book[]> {
  const response = await axiosInstance.post<Book[]>(
    `/api/v1/shelves/${shelfId}/books`,
    book
  )
  return response.data
}

export const useAddBookToShelf = (queryClient: QueryClient) =>
  useMutation({
    mutationFn: async ({ book, shelfId }: { book: Book; shelfId: number }) => {
      addBookToShelf(book, shelfId)
    },
    onSuccess: () => queryClient.invalidateQueries(),
  })
