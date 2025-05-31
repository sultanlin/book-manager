import { Shelf } from "@/types/api"
import { User } from "@/types/api"
import { create } from "zustand"

type UserStore = User & {
  assignTemporaryToken: () => void
}

export const useGlobalStore = create<UserStore>((set) => ({
  username: "",
  token: "",
  assignTemporaryToken: () => {
    set(() => ({
      token:
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWx0YW4yIiwiaWF0IjoxNzQ4Njc5MDQ5LCJleHAiOjE3NDg3NjU0NDl9.Q3u2UMncnIDEawRejRe64kxeApQs9tXBz6yhoyJuahE",
    }))
  },
}))
