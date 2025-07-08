import { axiosInstance } from "@/lib/api-client"
import { Book } from "@/types/api"
import { queryOptions, useQuery } from "@tanstack/react-query"
import { AxiosError } from "axios"

export async function getBook(bookId: number): Promise<Book> {
  const response = await axiosInstance.get<Book>(`api/v1/books/${bookId}`)
  return {
    ...response.data,
    releaseDate: new Date(response.data.releaseDate),
  }
}

export const getBookQueryOptions = (bookId: number) => {
  return queryOptions({
    queryKey: ["books", bookId],
    queryFn: () => getBook(bookId),
    retry: (failureCount, error: AxiosError) => {
      if (error.status === 404) return false // don't retry if resource not found
      return failureCount < 3
    },
  })
}

export const useBook = (bookId: number) => {
  return useQuery({
    ...getBookQueryOptions(bookId),
  })
}
