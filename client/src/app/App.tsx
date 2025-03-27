import { useEffect, useState } from "react"
// import "./App.css"
import "../assets/style.css"
import MainPage from "../features/home/MainPage"

function App() {
  const [data, setData] = useState("")
  useEffect(() => {
    fetch("http://192.168.1.44:8080/", {
      method: "GET",
      // mode: "cors",
    })
      .then((res) => res.json())
      .then((res) => setData(res.message))
      .catch((e) => console.log(e))
  }, [])
  console.log(data)

  return (
    <div>
      <MainPage />
    </div>
  )
}

export default App
