import { StrictMode } from "react"
import { createRoot } from "react-dom/client"
import "@/assets/style.css"
import Search from "@/app/pages/search"

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <Search />
  </StrictMode>,
)
