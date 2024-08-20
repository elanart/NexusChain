import React, { useRef, useState } from "react";
import APIs, { endpoints } from "../../configs/APIs";
import { Navigate, useNavigate } from "react-router";

const Register = () => {
  const [fullName, setFullname] = useState("");
  const [address, setAddress] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [paymentTerms, setPaymentTerms] = useState("");
  const [role, setRole] = useState("ROLE_DISTRIBUTOR");

  const avatar = useRef();

  const navigate = useNavigate(); // Sử dụng useNavigate

  const roles = [
    { "Nhà cung cấp": "ROLE_SUPPLIER" },
    { "Đại lý": "ROLE_DISTRIBUTOR" },
    { "Đối tác vận chuyển": "ROLE_CARRIER" },
  ];

  const roleOptions = roles.map((role) => {
    const [label, value] = Object.entries(role)[0];
    return { label, value };
  });

  const changeRole = (event) => {
    setRole(event.target.value);
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      let form = new FormData();
      form.append('fullName', fullName);
      form.append('address', address);
      form.append('phone', phone);
      form.append('email', email);
      form.append('role', role);
      form.append('username', username);
      form.append('password', password);

      if (avatar.current && avatar.current.files.length > 0) {
        form.append('avatar', avatar.current.files[0]);
      }

      let res = await APIs.post(endpoints['registerUser'], form, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });

      if (res.status === 201) {
        alert("Đăng ký tài khoản thành công")
        navigate("/Login")
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
        <h1 className="text-2xl font-bold mb-6 text-center">
          Đăng Ký Tài Khoản
        </h1>
        <form onSubmit={handleRegister}>
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
              value={fullName}
              onChange={(e) => setFullname(e.target.value)}
              required
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
              value={address}
              onChange={(e) => setAddress(e.target.value)}
              required
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
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
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
              value={phone}
              onChange={(e) => setPhone(e.target.value)}
              required
            />
          </div>
          <div className="mt-3">
            <label
              for="username"
              class="block text-sm font-medium text-gray-700"
            >
              Tên tài khoản
            </label>
            <input
              type="text"
              id="username"
              name="username"
              class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div className="mt-3">
            <label
              for="password"
              class="block text-sm font-medium text-gray-700"
            >
              Mật khẩu
            </label>
            <input
              type="password"
              id="password"
              name="password"
              class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <div className="mt-3">
            <label for="role" class="block text-sm font-medium text-gray-700">
              Loại tài khoản
            </label>
            <select
              id="role"
              name="role"
              value={role}
              onChange={changeRole}
              className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            >
              {roleOptions.map((option) => (
                <option key={option.value} value={option.value}>
                  {option.label}
                </option>
              ))}
            </select>
          </div>
          <div className="mt-3">
            <label for="avatar" class="block text-sm font-medium text-gray-700">
              Chọn ảnh đại diện
            </label>
            <input
              type="file"
              id="avatar"
              name="avatar"
              class="mt-1 block w-full px-3 py-2  border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              ref={avatar}
              accept=".png, .jpg, .webp"
              required
            />
          </div>
          <div className="mt-4">
            <button
              type="submit"
              className="block w-full px-3 py-2 border rounded-2xl bg-blue-700 text-white"
            >
              Đăng ký
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Register;
