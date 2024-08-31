import React, { useContext, useEffect, useState } from "react";
import { MyUserContext } from "../../App";
import { authAPIs, endpoints } from "../../configs/APIs";

const Home = () => {
  const user = useContext(MyUserContext);

  const [orderType, setOrderType] = useState("");
  const [orders, setOrders] = useState([]);
  const [suppliers, setSuppliers] = useState([]);
  const [products, setProducts] = useState([]);

  const [supplierId, setSupplierId] = useState();
  const [orderId, setOrderId] = useState();

  //Tạo đơn hàng
  const handleCreateOrder = async () => {
    try {
      const orderData = {
        status: "PENDING",
        userId: user.id,
      };
      if (user.role === "ROLE_SUPPLIER") {
        orderData.type = "OUTBOUND";
      }
      if (user.role === "ROLE_DISTRIBUTOR") {
        orderData.type = "INBOUND";
      }

      let res = await authAPIs().post(endpoints["createOrder"], orderData, {
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (res.status === 201) {
        alert("Tạo đơn hàng thành công!");
        console.log(res.data);
        setOrders([...orders, res.data]);
        loadingAllOrder();
      }
    } catch (error) {
      console.error(error);
    }
  };

  const handleOrderDetail = async (orderId) => {
    try {
      console.log(orderId);
    } catch (error) {
      console.log(error);
    }
  };

  //Get tất cả đơn hàng của user
  const loadAllOrder = async () => {
    try {
      let res = await authAPIs().get(endpoints["getAllOrder"]);
      setOrders(res.data);
      // console.log(res.data);
    } catch (error) {
      console.log(error);
    }
  };
  //Get tất cả nhà cung cấp
  const loadAllSupplier = async () => {
    try {
      let res = await authAPIs().get(endpoints["getAllSupplier"]);
      setSuppliers(res.data);
      // console.log(res.data);
    } catch (error) {
      console.log(error);
    }
  };
  useEffect(() => {
    loadAllOrder();
    loadAllSupplier();
  }, []);
  
  //Get tất cả sản phẩm của nhà cung cấp
  const loadAllProductOwnSupplier = async (supplierId) => {
    try {
      let res = await authAPIs().get(endpoints["getAllProduct"](supplierId));
      setProducts(res.data);
    } catch (error) {
      console.log(error);
    }
  }
  useEffect(() => {
    if (supplierId) {
      console.log("Fetching products for supplierId:", supplierId);
      loadAllProductOwnSupplier(supplierId);
    }
  }, [supplierId]);
  
  return (
    <div className="w-4/5 h-screen mx-auto mt-12 bg-gray-100 relative">
      <div className="mt-12">
        {user &&
          (user.role === "ROLE_SUPPLIER" ||
            user.role === "ROLE_DISTRIBUTOR") && (
            <>
              <button
                className="w-48 h-16 text-white mt-12 bg-blue-500 rounded-full"
                onClick={() => handleCreateOrder()}
              >
                Tạo đơn hàng
              </button>
            </>
          )}

        {user && user.role === "ROLE_CARRIER" && (
          <>
            <button
              className="w-60 h-20 text-white mt-12 bg-blue-500 rounded-full"
              onClick={() => handleViewListContract()}
            >
              Xem danh sách hợp đồng vận chuyển của bạn
            </button>
          </>
        )}
      </div>
      {user && (
        <div>
          <div class="container mx-auto py-8 w-2/5 absolute left-0">
            <h2 className="text-center text-2xl mb-2">
              DANH SÁCH ĐƠN HÀNG CỦA BẠN
            </h2>
            <table class="min-w-full bg-white border border-gray-200">
              <thead>
                <tr>
                  <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border-b">
                    ID
                  </th>
                  <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border-b">
                    Ngày đặt
                  </th>
                  <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border-b">
                    Trạng thái
                  </th>
                  <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider border-b">
                    Loại
                  </th>
                </tr>
              </thead>
              <tbody>
                {orders !== null &&
                  orders.map((order) => (
                    <tr
                      onClick={() => {
                        handleOrderDetail(order.id), setOrderId(order.id);
                      }}
                    >
                      <td class="px-6 py-4 whitespace-nowrap border-b">
                        {order.id}
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap border-b">
                        {(() => {
                          const date = new Date(order.orderDate);
                          const hours = String(date.getHours()).padStart(
                            2,
                            "0"
                          );
                          const minutes = String(date.getMinutes()).padStart(
                            2,
                            "0"
                          );
                          const day = String(date.getDate()).padStart(2, "0");
                          const month = String(date.getMonth() + 1).padStart(
                            2,
                            "0"
                          ); // Tháng được tính từ 0-11, nên cần +1
                          const year = date.getFullYear();
                          return `${hours}:${minutes} ${day}-${month}-${year}`;
                        })()}
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap border-b">
                        {(() => {
                          switch (order.status) {
                            case "PENDING":
                              return "Chưa giải quyết";
                            case "COMPLETED":
                              return "Hoàn thành";
                            case "CANCELLED":
                              return "Đã hủy";
                            default:
                              return order.status;
                          }
                        })()}
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap border-b">
                        {order.type === "INBOUND"
                          ? "Nhập kho"
                          : order.type === "OUTBOUND"
                          ? "Xuất kho"
                          : order.type}
                      </td>
                    </tr>
                  ))}
              </tbody>
            </table>
          </div>
          <div class="container mx-auto py-8 w-2/5 absolute right-0">
            <h2 className="text-center text-2xl mb-2">CHI TIẾT ĐƠN HÀNG</h2>
            <form class="min-w-full bg-white border border-gray-200">
              <div className="mt-3">
                <label
                  for="orderId"
                  class="block text-sm font-medium text-gray-700"
                >
                  ID đơn hàng
                </label>
                <input
                  type="text"
                  id="orderId"
                  name="orderId"
                  class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                  value={orderId}
                  onChange={(e) => setFullname(e.target.value)}
                  disabled
                />
              </div>
              <div className="mt-3">
                <label
                  for="supplier"
                  class="block text-sm font-medium text-gray-700"
                >
                  Supplier
                </label>
                <select
                  id="supplier"
                  name="supplier"
                  class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                  onChange={(e) => setSupplierId(e.target.value)} // Set supplierId on selection
                >
                  {suppliers.map((supplier) => (
                    <option key={supplier.id} value={supplier.id}>
                      {supplier.fullName}
                    </option>
                  ))}
                </select>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default Home;
