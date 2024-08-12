import React from 'react'
import { Link } from 'react-router-dom'

const Header = () => {
  return (
    <div className='w-full h-12 bg-blue-700 relative'>
      <p className='absolute left-4 top-1/2 -translate-y-1/2 font-bold text-white'>NEXUS CHAIN</p>
      <Link className='absolute right-4 top-1/2 -translate-y-1/2 text-white' to="/Register">Đăng ký</Link>
    </div>
  )
}

export default Header
