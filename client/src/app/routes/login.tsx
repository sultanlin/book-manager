import TextInput from "@/components/text-input"
import { AuthForm, loginAction } from "@/features/auth"
import { ActionFunctionArgs } from "react-router"

export async function clientAction(actionArgs: ActionFunctionArgs) {
  return loginAction(actionArgs)
}

function Login() {
  return (
    <div className="form">
      <AuthForm>
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
