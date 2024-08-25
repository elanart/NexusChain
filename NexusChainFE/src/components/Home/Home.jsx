import React, { useContext, useState } from 'react'
import { MyUserContext } from '../../App'
import { authAPIs, endpoints } from '../../configs/APIs'

const Home = () => {
  const user = useContext(MyUserContext)
  
  const [orderType, setOrderType] = useState("")

  const handleCreateOrder = async () => {
    try {
      const orderData = {
        "status": "PENDING",
        "userId": user.id,
      }
      if (user.role === "ROLE_SUPPLIER") {
        orderData.type = "INBOUND";
      }
      if (user.role === "ROLE_DISTRIBUTOR") {
        orderData.type = "OUTBOUND";
      }

      let res = await authAPIs().post(endpoints['createOrder'], orderData, {
        headers: {
          "Content-Type": "application/json",
        },
      })

      if (res.status === 201) {
        alert("Tạo đơn hàng thành công!");
      }

    } catch (error) {
      console.error(error);
    }
  }

  const handleViewListContract = () => {

  }

  return (
    <div className="w-4/5 h-screen mx-auto mt-12 bg-gray-100">
     <div className='mt-12'>
      {user && (user.role === "ROLE_SUPPLIER" || user.role === "ROLE_DISTRIBUTOR") && (
          <>
            <button className='w-48 h-16 text-white mt-12 bg-blue-500 rounded-full' onClick={() => handleCreateOrder()}>Tạo đơn hàng</button>
          </>
        )}

     {user && user.role === "ROLE_CARRIER" && (
      <>
        <button className='w-60 h-20 text-white mt-12 bg-blue-500 rounded-full' onClick={() => handleViewListContract()}>Xem danh sách hợp đồng vận chuyển của bạn</button>
      </>
    )}
     </div>
    </div>
  )
}

export default Home
