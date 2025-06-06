import { useUserStore } from "@/stores/store"
import { LoaderFunctionArgs, redirect } from "react-router-dom"

export default async function requireAuth({ request }: LoaderFunctionArgs) {
  const url = new URL(request.url)
  const token = useUserStore.getState().token
  const searchParams = url.searchParams.size
    ? `?${url.searchParams.toString()}`
    : ""
  if (!token) {
    return redirect(`/login?redirectTo=${url.pathname + searchParams || "/"}`)
  }
}
