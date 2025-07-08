import { axiosInstance } from "@/lib/api-client"
import { Shelf } from "@/types/api"
import { queryOptions, useQuery } from "@tanstack/react-query"
import { AxiosError } from "axios"

export async function getBookShelves(bookId: number): Promise<Shelf[]> {
  const response = await axiosInstance.get<Shelf[]>(
    `/api/v1/books/${bookId}/shelves`
  )
  return response.data
}

export const getBookShelvesQueryOptions = (bookId: number) => {
  return queryOptions({
    queryKey: ["shelves", bookId],
    queryFn: () => getBookShelves(bookId),
    retry: (failureCount, error: AxiosError) => {
      if (error.status === 404) return false // don't retry if resource not found
      return failureCount < 3
    },
  })
}

export const useBookShelves = (bookId: number) => {
  return useQuery({
    ...getBookShelvesQueryOptions(bookId),
  })
}
