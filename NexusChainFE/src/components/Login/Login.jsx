import React, { useState } from "react";
import APIs, { authAPIs, endpoints } from "../../configs/APIs";
import cookie from "react-cookies";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
        let res = await APIs.post(endpoints['login'], {
            "username": username,
            "password": password
        })
        console.log(res.data)
        cookie.save("token", res.data);
        console.log(cookie.load("token"))

        let user = await authAPIs().get(endpoints['current-user'])
        console.log(user.data)
        // cookie.save("user", user.data)
    } catch (error) {
        console.error(error)
    }

  }

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
        <h1 className="text-2xl font-bold mb-6 text-center">Đăng Nhập</h1>
        <form onSubmit={handleLogin}>
          <div className="mt-3">
            <label
              for="username"
              class="block text-sm font-medium text-gray-700"
            >
              Tên đăng nhập
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
          <div className="mt-4">
            <button
              type="submit"
              className="block w-full px-3 py-2 border rounded-2xl bg-blue-700 text-white"
            >
              Đăng nhập
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
