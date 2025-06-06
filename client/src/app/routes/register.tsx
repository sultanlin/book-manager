import TextInput from "@/components/text-input"
import { AuthForm } from "@/features/auth"

function Register() {
  return (
    <div>
      <AuthForm pendingMessage="Creating account...">
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
