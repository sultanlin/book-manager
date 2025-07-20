import { axiosInstance } from "@/lib/api-client"
import { Shelf, ShelfAddRequest } from "@/types/api"
import { QueryClient, useMutation } from "@tanstack/react-query"

export async function createShelf(shelfAdd: ShelfAddRequest): Promise<Shelf> {
  const response = await axiosInstance.post<Shelf>(`/api/v1/shelves`, shelfAdd)
  return response.data
}

export const useCreateShelf = (queryClient: QueryClient) =>
  useMutation({
    mutationFn: async ({ shelfAdd }: { shelfAdd: ShelfAddRequest }) => {
      createShelf(shelfAdd)
    },
    onSuccess: () => queryClient.invalidateQueries(),
  })
