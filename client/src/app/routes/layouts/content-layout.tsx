import { Logo, UserCard } from "@/features/header"
import { SearchBar } from "@/features/search"
import Sidebar from "@/features/sidebar"
import { useUserStore } from "@/stores/store"
import { LoaderFunctionArgs, redirect } from "react-router"
import { Outlet } from "react-router"

export async function clientLoader({ request }: LoaderFunctionArgs) {
  const token = useUserStore.getState().token
  if (!token) {
    const url = new URL(request.url)
    const searchParams = url.searchParams.size
      ? `?${url.searchParams.toString()}`
      : ""

    return redirect(`/login?redirectTo=${url.pathname + searchParams}`)
  }
}

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
