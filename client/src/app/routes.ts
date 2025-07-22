import {
  index,
  layout,
  route,
  type RouteConfig,
} from "@react-router/dev/routes"

export default [
  layout("./routes/layouts/auth-layout.tsx", [
    route("login", "./routes/login.tsx"),
    route("register", "./routes/register.tsx"),
  ]),
  // TODO: Consider catchall/splat
  layout("./routes/layouts/content-layout.tsx", [
    index("./routes/home.tsx"),
    route("search", "./routes/search.tsx"),
    route("shelves/:shelfId", "./routes/shelves.tsx"),
    route("books/:bookId", "./routes/books.tsx"),
    route("settings/shelves", "./routes/shelves-settings.tsx"),
  ]),
] satisfies RouteConfig
