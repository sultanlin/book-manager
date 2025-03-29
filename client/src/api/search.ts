// TODO: unmarshal json to appropriate obj

import BookProps from "@/types/BookProps"

// name, author, cover
export default function searchBooks(
  bookName: string,
  queryType: string = "book",
) {
  let typeParam = ""
  if (queryType) {
    typeParam = `type=${queryType}`
  }
  const queryParams = `?name=${bookName}&${typeParam}`
  console.log(queryParams)

  fetch("http://localhost:8080/api/v1/search" + queryParams)
    .then((res) => res.json())
    .then((data) => console.log(data))
    .catch((err) => console.log(err))
}
