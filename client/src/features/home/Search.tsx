import SearchIcon from "@mui/icons-material/Search"
function Search() {
  return (
    <form className="search">
      <div className="searchbar">
        <input
          type="text"
          name="search"
          id="search"
          placeholder="Search books or authors"
        />
        <label htmlFor="search">
          <SearchIcon className="icon" />
        </label>
      </div>
      <select>
        <option>Book</option>
        <option>Author</option>
      </select>
    </form>
  )
}

export default Search
