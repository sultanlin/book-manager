import { StrictMode } from "react"
import { createRoot } from "react-dom/client"
import "@/assets/style.css"
import Search from "@/app/pages/search"
import { createBrowserRouter, RouterProvider } from "react-router-dom"
import FirstPage from "@/app/pages/firstpage"

const router = createBrowserRouter([
  {
    path: "/",
    element: <FirstPage />,
  },
  {
    path: "/search",
    element: <Search />,
  },
])
createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)
