import TextInput from "@/components/text-input"
import { AuthForm, registerAction } from "@/features/auth"
import { ActionFunctionArgs } from "react-router"

export async function clientAction(actionArgs: ActionFunctionArgs) {
  return registerAction(actionArgs)
}

function Register() {
  return (
    <div>
      <AuthForm>
        <TextInput field="username" placeholder="ie. adminuser" />
        <TextInput
          field="password"
          placeholder="ie. pass1234"
          isPassword={true}
        />
        <TextInput
          field="passwordConfirmation"
          placeholder="must match password"
          isPassword={true}
        >
          Confirm Password
        </TextInput>
      </AuthForm>
    </div>
  )
}

export default Register
