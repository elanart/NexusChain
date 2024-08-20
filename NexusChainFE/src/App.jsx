import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/Header/Header";
import Footer from "./components/Footer/Footer";
import Home from "./components/Home/Home";
import Register from "./components/Register/Register";
import Login from "./components/Login/Login";
import { createContext, useReducer } from "react";
import MyUserReducer from "./reducers/MyUserReducer";
import UserDetails from "./components/User/UserDetails";

export const MyUserContext = createContext();
export const MyDispatchContext = createContext();

const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, null);
  return (
    <BrowserRouter>
      <MyUserContext.Provider value={user}> 
        <MyDispatchContext.Provider value={dispatch}>
          <Header />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/Register" element={<Register />} />
            <Route path="/Login" element={<Login />} />
            <Route path="/UserDetails" element={<UserDetails />} />
          </Routes>
          <Footer />
        </MyDispatchContext.Provider>
      </MyUserContext.Provider>
    </BrowserRouter>
  );
};
export default App;
