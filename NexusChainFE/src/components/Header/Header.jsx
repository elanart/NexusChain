import React from 'react'
import { Link } from 'react-router-dom'

const Header = () => {
  return (
    <div className='w-full h-12 bg-blue-700 fixed top-0 left-0 flex items-center justify-between px-4'>
      <Link className='font-bold text-white' to="/">NEXUS CHAIN</Link>
      <div className='space-x-4'>
        <Link className='text-white' to="/Login">Đăng nhập</Link>
        <Link className='text-white' to="/Register">Đăng ký</Link>
      </div>
    </div>
  )
}

export default Header
