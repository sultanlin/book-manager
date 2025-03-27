import BooksDisplay from "@/features/home/BooksDisplay"
import Header from "@/features/home/Header"
import Sidebar from "@/features/home/Sidebar"

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
