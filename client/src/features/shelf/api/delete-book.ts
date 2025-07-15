import { axiosInstance } from "@/lib/api-client"
import { QueryClient, useMutation } from "@tanstack/react-query"

export async function deleteBookFromShelf(
  bookId: number,
  shelfId: number
): Promise<void> {
  const resp = await axiosInstance.delete(
    `/api/v1/shelves/${shelfId}/books/${bookId}`
  )
  return resp.data
}

export const useDeleteBookFromShelf = (queryClient: QueryClient) =>
  useMutation({
    mutationFn: async ({
      shelfId,
      bookId,
    }: {
      shelfId: number
      bookId: number
    }) => {
      deleteBookFromShelf(bookId, shelfId)
    },
    onSuccess: () => queryClient.invalidateQueries(),
  })
