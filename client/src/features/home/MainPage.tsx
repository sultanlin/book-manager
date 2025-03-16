import BooksDisplay from "./BooksDisplay"
import Header from "./Header"
import Sidebar from "./Sidebar"

function MainPage() {
  {
    // Holy grail, with header, sidebar (nav), and main content
    // Make sidebar disappear/appear feature
  }
  return (
    <div className="container grid">
      <Header />
      <Sidebar />
      <BooksDisplay />
    </div>
  )
}

export default MainPage
