import { layout, route, type RouteConfig } from "@react-router/dev/routes"

export default [
  layout("./routes/layouts/auth-layout.tsx", [
    route("login", "./routes/login.tsx"),
    route("register", "./routes/register.tsx"),
  ]),
  // TODO: add errorboundary
  layout("./routes/layouts/content-layout.tsx", [
    route("search", "./routes/search.tsx"),
    route("shelves/:shelfId", "./routes/shelves.tsx"),
  ]),
] satisfies RouteConfig
