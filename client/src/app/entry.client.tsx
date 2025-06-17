import { StrictMode } from "react"
import { hydrateRoot } from "react-dom/client"
import { HydratedRouter } from "react-router/dom"
import { QueryClient, QueryClientProvider } from "@tanstack/react-query"

const queryClient = new QueryClient()

hydrateRoot(
  document,
  <StrictMode>
    <QueryClientProvider client={queryClient}>
      <HydratedRouter />
    </QueryClientProvider>
  </StrictMode>
)
