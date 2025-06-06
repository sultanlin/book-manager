import SearchIcon from "@mui/icons-material/Search"
import { useState } from "react"
import { Form } from "react-router-dom"

function SearchBar() {
  const [value, setValue] = useState("")
  return (
    <Form className="searchbar" action="/search">
      <input
        type="text"
        name="search"
        id="search"
        placeholder="Search books or authors"
        onChange={(e) => setValue(e.target.value)}
        value={value}
      />
      <SearchIcon className="icon" />
    </Form>
  )
}

export default SearchBar
