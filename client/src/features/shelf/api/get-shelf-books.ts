import { axiosInstance } from "@/lib/api-client"
import { Book } from "@/types/api"
import { queryOptions, useQuery } from "@tanstack/react-query"

export async function getShelfBooks(shelfId: number): Promise<Book[]> {
  const response = await axiosInstance.get<Book[]>(
    `/api/v1/shelves/${shelfId}/books`
  )
  return response.data
}

export const getShelfBooksQueryOptions = (shelfId: number) => {
  return queryOptions({
    queryKey: ["shelves", shelfId],
    queryFn: () => getShelfBooks(shelfId),
  })
}

export const useShelfBooks = (shelfId: number) => {
  return useQuery({
    ...getShelfBooksQueryOptions(shelfId),
  })
}
