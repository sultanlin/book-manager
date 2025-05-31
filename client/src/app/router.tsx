import { createBrowserRouter, redirect } from "react-router-dom"
import Login from "@/app/routes/login"
import { useGlobalStore } from "@/stores/store"
import ContentLayout from "@/app/routes/content-layout"
import Search from "@/app/routes/search"

async function requireAuth() {
  const token = useGlobalStore.getState().token
  if (!token) {
    return redirect("/login")
  }
  return null
}

export const router = createBrowserRouter([
  {
    path: "/login",
    element: <Login />,
    // action: Logic for processing login form, put redirect to previous route here
  },
  {
    // TODO: Change ProtectedRoute name (aka ProtectedLayout)
    element: <ContentLayout />,
    loader: requireAuth,
    children: [
      {
        path: "search",
        element: <Search />,
      },
    ],
  },
])
