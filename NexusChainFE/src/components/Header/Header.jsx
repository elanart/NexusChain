import React, { useContext } from 'react'
import { Link } from 'react-router-dom'
import { MyDispatchContext, MyUserContext } from '../../App'
import { Navigate, useNavigate } from "react-router";

const Header = () => {
  const user = useContext(MyUserContext);
  const dispatch = useContext(MyDispatchContext);
  const nav = useNavigate();

  const handleLogout = () => {
    dispatch({"type":"logout"})
    nav('/')
  }

  return (
    <div className='w-full h-12 bg-blue-700 fixed top-0 left-0 flex items-center justify-between px-4'>
      <Link className='font-bold text-white' to="/">NEXUS CHAIN</Link>
      <div className='space-x-4'>
        {user === null? 
        <>
          <Link className='text-white' to="/Login">Đăng nhập</Link> 
          <Link className='text-white' to="/Register">Đăng ký</Link></>
        :<>
          <Link className='text-white' to="/UserDetails">Hi {user.fullName} !</Link>
          <button className='text-white' onClick={handleLogout}>Đăng xuất</button>
        </>}
      </div>
    </div>
  )
}

export default Header
