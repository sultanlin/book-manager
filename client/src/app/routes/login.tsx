import TextInput from "@/components/text-input"
import { AuthForm } from "@/features/auth"

function Login() {
  return (
    <div className="form">
      <AuthForm pendingMessage="Logging in...">
        <TextInput field="username" placeholder="ie. adminuser" />
        <TextInput
          field="password"
          placeholder="ie. pass1234"
          isPassword={true}
        />
      </AuthForm>
    </div>
  )
}

export default Login
