import { createBrowserRouter } from "react-router-dom"
import Login from "@/app/routes/login"
import Search from "@/app/routes/search"
import { loginAction, registerAction, requireAuth } from "@/features/auth"
import AuthLayout from "@/components/layouts/auth-layout"
import Register from "./routes/register"
import ContentLayout from "@/components/layouts/content-layout"

export const router = createBrowserRouter([
  {
    element: <AuthLayout />,
    children: [
      {
        path: "login",
        element: <Login />,
        action: loginAction,
      },
      {
        path: "register",
        element: <Register />,
        action: registerAction,
      },
    ],
  },
  {
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
