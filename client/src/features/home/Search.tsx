import SearchIcon from "@mui/icons-material/Search"
function Search() {
  return (
    <form className="search">
      <input type="text" name="search" id="search" placeholder="Search books" />
      <label htmlFor="search">
        <SearchIcon className="icon" />
      </label>
    </form>
  )
}

export default Search
