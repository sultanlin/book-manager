import { User } from "@/types/api"
import { create } from "zustand"

type UserStore = User & {
  assignToken: (token: string) => void
  assignUsername: (username: string) => void
  logout: () => void
}

// TODO: Change name to useAuthStore
export const useUserStore = create<UserStore>((set) => ({
  username: "",
  token: "",
  assignToken: (token: string) => {
    set(() => ({ token: token }))
  },
  assignUsername: (username: string) => {
    set(() => ({ username: username }))
  },
  logout: () => {
    set(() => ({ username: "", token: "" }))
  },
}))
