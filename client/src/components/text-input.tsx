import { ReactNode } from "react"

type TextInputProps = {
  field: string
  styles?: string[]
  children?: ReactNode
  defaultValue?: string
  isPassword?: boolean
  placeholder?: string
}

function capitalize(word: string): ReactNode {
  return word.charAt(0).toUpperCase() + word.slice(1)
}

function TextInput({
  field,
  styles,
  children = capitalize(field),
  isPassword = false,
  ...restProps
}: TextInputProps) {
  return (
    <div className={styles?.join(" ")}>
      <label htmlFor={field}>{children}</label>
      <input
        type={isPassword ? "password" : "text"}
        name={field}
        id={field}
        {...restProps}
      />
    </div>
  )
}

export default TextInput
