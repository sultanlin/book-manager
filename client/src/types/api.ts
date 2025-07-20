// Remove "Props" from names, use "Props" mainly for comp type (aka ReactProps)
export type User = {
  username: string
  token: string
}

export type Book = {
  id: number
  title: string
  author: string
  cover: string
  pages: number
  rating: number
  ratingsCount: number
  description: string
  releaseDate: Date
  slug: string
  subtitle: string
}

export type Shelf = {
  id: number
  name: string
}

export type ShelfAddRequest = {
  shelfName: string
}

export type LoginCredentials = {
  username: string
  password: string
}

export type SignupCredentials = LoginCredentials & {
  passwordConfirmation: string
}
