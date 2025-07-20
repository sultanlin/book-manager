import { axiosInstance } from "@/lib/api-client"
import { QueryClient, useMutation } from "@tanstack/react-query"

export async function deleteShelf(shelfId: number): Promise<void> {
  await axiosInstance.delete(`/api/v1/shelves/${shelfId}`)
}

export const useDeleteShelf = (queryClient: QueryClient) =>
  useMutation({
    mutationFn: async ({ shelfId }: { shelfId: number }) => {
      deleteShelf(shelfId)
    },
    onSuccess: () => queryClient.invalidateQueries(),
  })
