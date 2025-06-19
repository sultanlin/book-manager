import { axiosInstance } from "@/lib/api-client"
import { Book } from "@/types/api"
import { queryOptions, useQuery } from "@tanstack/react-query"

export async function searchBooks(
  bookName: string,
  queryType: string = "book"
): Promise<Book[]> {
  const response = await axiosInstance.get<Book[]>("/api/v1/search", {
    params: {
      name: bookName,
      type: queryType,
    },
  })
  return response.data
}

export const getSearchQueryOptions = (bookName: string) => {
  return queryOptions({
    queryKey: ["search", bookName],
    queryFn: () => searchBooks(bookName),
  })
}

export const useSearch = (bookName: string) => {
  return useQuery({
    ...getSearchQueryOptions(bookName),
  })
}
