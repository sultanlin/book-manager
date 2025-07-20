import { axiosInstance } from "@/lib/api-client"
import { Shelf, ShelfAddRequest } from "@/types/api"
import { QueryClient, useMutation } from "@tanstack/react-query"

export async function renameShelf(
  shelfId: number,
  shelfAdd: ShelfAddRequest
): Promise<Shelf> {
  const response = await axiosInstance.put<Shelf>(
    `/api/v1/shelves/${shelfId}`,
    shelfAdd
  )
  return response.data
}

export const useRenameShelf = (queryClient: QueryClient) =>
  useMutation({
    mutationFn: async ({
      shelfId,
      shelfAdd,
    }: {
      shelfId: number
      shelfAdd: ShelfAddRequest
    }) => {
      renameShelf(shelfId, shelfAdd)
    },
    onSuccess: () => queryClient.invalidateQueries(),
  })
