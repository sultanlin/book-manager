import BookProps from "@/types/BookProps"

export default async function searchBooks(
  bookName: string,
  queryType: string = "book",
): Promise<BookProps[]> {
  const books: BookProps[] = []
  let typeParam = ""
  if (queryType) {
    typeParam = `type=${queryType}`
  }
  const queryParams = `?name=${bookName}&${typeParam}`
  console.log(queryParams)

  const response = await fetch(
    "http://192.168.1.44:8080/api/v1/search" + queryParams,
  )
  const data = await response.json()

  data.results.hits.map(
    (hit: {
      document: {
        author_names: [string]
        title: string
        image: { url: string }
      }
    }) => {
      books.push({
        author: hit.document.author_names[0],
        name: hit.document.title,
        cover: hit.document.image.url,
      })
    },
  )

  return books
}
