import { BookMetadata } from "@/types/BookMetadata"

export default async function searchBooks(
  bookName: string,
  queryType: string = "book",
): Promise<BookMetadata[]> {
  let typeParam = ""
  if (queryType) {
    typeParam = `type=${queryType}`
  }
  const queryParams = `?name=${bookName}&${typeParam}`
  console.log(queryParams)

  const response = await fetch(
    "http://192.168.1.44:8080/api/v1/search" + queryParams,
  )
  const books = await response.json()

  return books
}
