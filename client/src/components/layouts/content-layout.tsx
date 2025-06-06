import { Logo, UserCard } from "@/features/header"
import { SearchBar } from "@/features/search"
import Sidebar from "@/features/sidebar"

import { Outlet } from "react-router-dom"

function ContentLayout() {
  return (
    // Holy grail, with header, sidebar (nav), and main content
    <div className="container grid">
      <header className="header">
        <Logo />
        <SearchBar />
        <UserCard />
        {/*  TODO: Make sidebar disappear/appear feature */}
      </header>
      <Sidebar />
      <main className="content">
        <Outlet />
      </main>
    </div>
  )
}

export default ContentLayout
