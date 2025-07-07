import { StrictMode } from "react"
import { hydrateRoot } from "react-dom/client"
import { HydratedRouter } from "react-router/dom"
import {
  DefaultOptions,
  QueryClient,
  QueryClientProvider,
} from "@tanstack/react-query"

const queryConfig = {
  queries: {
    refetchOnWindowFocus: false,
    retry: false,
    // staleTime: 1000 * 60,
    staleTime: 1000 * 60 * 60,
  },
} satisfies DefaultOptions

const queryClient = new QueryClient({
  defaultOptions: queryConfig,
})

hydrateRoot(
  document,
  <StrictMode>
    <QueryClientProvider client={queryClient}>
      <HydratedRouter />
    </QueryClientProvider>
  </StrictMode>
)
