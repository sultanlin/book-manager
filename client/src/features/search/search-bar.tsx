import SearchIcon from "@mui/icons-material/Search"
import { useState } from "react"
import { Form } from "react-router-dom"

function SearchBar() {
  const [value, setValue] = useState("")
  return (
    <Form className="search" action="/search">
      <div className="searchbar">
        <input
          type="text"
          name="search"
          id="search"
          placeholder="Search books or authors"
          onChange={(e) => setValue(e.target.value)}
          value={value}
        />
        <label htmlFor="search">
          <SearchIcon className="icon" />
        </label>
      </div>
      {/* <select> */}
      {/*   <option>Book</option> */}
      {/*   <option>Author</option> */}
      {/* </select> */}
    </Form>
  )
}

export default SearchBar
