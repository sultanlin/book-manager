import { Links, Meta, Outlet, Scripts, ScrollRestoration } from "react-router"
import "@/assets/style.css"

export function ErrorBoundary() {
  return (
    <div className="boundary" role="alert">
      <h2>Ooops, something went wrong :( </h2>
      <button onClick={() => window.location.assign(window.location.origin)}>
        Refresh
      </button>
    </div>
  )
}

export function Layout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <head>
        <meta charSet="UTF-8" />
        <link rel="icon" type="image/svg+xml" href="vite.svg" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Library</title>
        <Meta />
        <Links />
      </head>
      <body>
        {children}
        <ScrollRestoration />
        <Scripts />
      </body>
    </html>
  )
}

export default function Root() {
  return <Outlet />
}
