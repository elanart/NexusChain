import React, { useContext, useRef, useState } from "react";
import { MyUserContext } from "../../App";
import { authAPIs, endpoints } from "../../configs/APIs";

const UserDetails = () => {
  const user = useContext(MyUserContext);

  const [fullName, setFullname] = useState(user.fullName);
  const [address, setAddress] = useState(user.address);
  const [email, setEmail] = useState(user.email);
  const [phone, setPhone] = useState(user.phone);
  const [role, setRole] = useState(user.role);
  const [paymentTerms, setPaymentTerms] = useState("");
  const [cooperationTerms, setCooperationTerms] = useState("");

  const avatar = useRef();

  const roleMapping = {
    ROLE_SUPPLIER: "Nhà cung cấp",
    ROLE_DISTRIBUTOR: "Đại lý",
    ROLE_CARRIER: "Đối tác vận chuyển",
    ROLE_ADMIN: "Quản trị viên",
  };

  const handleUpdateUser = async (e) => {
    e.preventDefault();
    try {
      // Tạo đối tượng userData với các thông tin cần cập nhật
      const userData = {
      
      };
      // Kiểm tra và thêm các điều kiện đặc biệt nếu có
      if (user.role === "ROLE_SUPPLIER" && paymentTerms !== null) {
        userData.paymentTerms = paymentTerms;
        userData.cooperationTerms = "Rỗng";
      }

      if (user.role === "ROLE_CARRIER" && cooperationTerms !== null) {
        userData.paymentTerms = "Rỗng";
        userData.cooperationTerms = cooperationTerms;
      }

      let res = await authAPIs().patch(endpoints['updateUser'], userData, {
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (res.status === 200) {
        alert("Cập nhật thành công!");
      }

    } catch (error) {
      console.error(error);
      alert("Đã xảy ra lỗi khi cập nhật thông tin tài khoản.");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md mt-16 mb-16">
        <h1 className="text-2xl font-bold mb-6 text-center">
          Thông Tin Tài Khoản
        </h1>
        <form>
          <div className="mt-3">
            <label
              for="fullName"
              class="block text-sm font-medium text-gray-700"
            >
              Tên đầy đủ
            </label>
            <input
              type="text"
              id="fullName"
              name="fullName"
              class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              value={user.fullName}
              onChange={(e) => setFullname(e.target.value)}
              disabled
            />
          </div>
          <div className="mt-3">
            <label
              for="address"
              class="block text-sm font-medium text-gray-700"
            >
              Địa chỉ
            </label>
            <input
              type="text"
              id="address"
              name="address"
              class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              value={user.address}
              onChange={(e) => setAddress(e.target.value)}
              disabled
            />
          </div>
          <div className="mt-3">
            <label for="email" class="block text-sm font-medium text-gray-700">
              Email
            </label>
            <input
              type="email"
              id="email"
              name="email"
              class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              value={user.email}
              onChange={(e) => setEmail(e.target.value)}
              disabled
            />
          </div>
          <div className="mt-3">
            <label for="phone" class="block text-sm font-medium text-gray-700">
              Số điện thoại
            </label>
            <input
              type="number"
              id="phone"
              name="phone"
              class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              value={user.phone}
              onChange={(e) => setPhone(e.target.value)}
              disabled
            />
          </div>
          <div className="mt-3">
            <label for="role" class="block text-sm font-medium text-gray-700">
              Loại tài khoản
            </label>
            <input
              type="text"
              id="role"
              name="role"
              class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              value={roleMapping[user.role] || "Không xác định"}
              disabled
            />
          </div>
          {user && user.role === "ROLE_SUPPLIER" && (
            <>
              <div className="mt-3">
                <label
                  for="paymentTerms"
                  className="block text-sm font-medium text-gray-700"
                >
                  Điều kiện thanh toán
                </label>
                <input
                  type="text"
                  id="paymentTerms"
                  name="paymentTerms"
                  className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                  value={paymentTerms}
                  onChange={(e) => setPaymentTerms(e.target.value)}
                  required
                />
              </div>
            </>
          )}
          {user && user.role === "ROLE_CARRIER" && (
            <>
              <div className="mt-3">
                <label
                  for="cooperationTerms"
                  className="block text-sm font-medium text-gray-700"
                >
                  Điều kiện hợp tác
                </label>
                <input
                  type="text"
                  id="cooperationTerms"
                  name="cooperationTerms"
                  className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                  value={cooperationTerms}
                  onChange={(e) => setCooperationTerms(e.target.value)}
                  required
                />
              </div>
            </>
          )}
          <div className="mt-3">
            <label for="avatar" class="block text-sm font-medium text-gray-700">
              Ảnh đại diện
            </label>
            <div className="flex justify-center">
              <img src={user.avatar} alt="avatar" className="w-48 h-48" />
            </div>
          </div>
          <div className="mt-4">
            <button
              className="block w-full px-3 py-2 border rounded-2xl bg-blue-700 text-white"
              onClick={handleUpdateUser}
            >
              Cập nhật tài khoản
            </button>
          </div>
          <div className="mt-4">
            <button className="block w-full px-3 py-2 border rounded-2xl bg-red-600 text-white">
              Xóa tài khoản
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default UserDetails;
