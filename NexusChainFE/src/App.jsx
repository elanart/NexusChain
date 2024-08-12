import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/Header/Header";
import Footer from "./components/Footer/Footer";
import Home from "./components/Home/Home";
import Register from "./components/Register/Register";

const App = () => {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/"  element={<Home/>}/>
        <Route path="/register"  element={<Register/>}/>
      </Routes>
      <Footer/>
    </BrowserRouter>
  );
}
export default App;