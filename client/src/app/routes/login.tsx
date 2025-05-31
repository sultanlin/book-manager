import { useNavigate } from "react-router-dom"
import { useGlobalStore } from "@/stores/store"

function Login() {
  const userToken = useGlobalStore((state) => state.token)
  const assignToken = useGlobalStore((state) => state.assignTemporaryToken)
  const navigate = useNavigate()

  const handleLogin = async () => {
    assignToken()
    if (!userToken) navigate("/search?name=harry+potter", { replace: true }) // redirect to original page or home
  }

  return (
    <div>
      <h2>Login Page</h2>
      <button onClick={handleLogin}>Log In without cred</button>
    </div>
  )
}

export default Login
