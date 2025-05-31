import { useGlobalStore } from "@/stores/store"
import { Book } from "@/types/api"

export default async function searchBooks(
  bookName: string,
  queryType: string = "book",
): Promise<Book[]> {
  let typeParam = ""
  if (queryType) {
    typeParam = `type=${queryType}`
  }
  const queryParams = `?name=${bookName}&${typeParam}`
  console.log(queryParams)

  const token = useGlobalStore.getState().token
  const response = await fetch(
    "http://192.168.1.44:8080/api/v1/search" + queryParams,
    {
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
    },
  )
  const books = await response.json()

  return books
}
