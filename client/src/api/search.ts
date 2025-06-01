import { axiosInstance } from "@/lib/api-client"
import { Book } from "@/types/api"

export default async function searchBooks(
  bookName: string,
  queryType: string = "book",
): Promise<Book[]> {
  return axiosInstance
    .get<Book[]>("/api/v1/search", {
      params: {
        name: bookName,
        type: queryType,
      },
    })
    .then((response) => response.data)
}
