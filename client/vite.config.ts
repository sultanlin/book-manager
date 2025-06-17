import { defineConfig } from "vite"
import { reactRouter } from "@react-router/dev/vite"

// https://vite.dev/config/
export default defineConfig({
  plugins: [reactRouter()],
  resolve: {
    alias: [
      {
        // @src: "/src",
        find: "@",
        replacement: "/src",
      },
    ],
  },
})
