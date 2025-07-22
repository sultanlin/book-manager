import { User } from "@/types/api"
import { QueryClient } from "@tanstack/react-query"
import { create } from "zustand"
import { persist } from "zustand/middleware"

type UserStore = User & {
  assignToken: (token: string) => void
  assignUsername: (username: string) => void
  logout: (queryClient: QueryClient) => void
}

export const useUserStore = create<UserStore>()(
  persist(
    (set) => ({
      username: "",
      token: "",
      assignToken: (token: string) => {
        set(() => ({ token: token }))
      },
      assignUsername: (username: string) => {
        set(() => ({ username: username }))
      },
      logout: (queryClient: QueryClient) => {
        queryClient.invalidateQueries()
        set(() => ({ username: "", token: "" }))
      },
    }),
    {
      name: "book-manager-persists",
    }
  )
)
