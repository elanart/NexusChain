import axios from "axios"
import cookie from "react-cookies";

const BASE_URL = "http://localhost:8080/nexuschain/api"

export const endpoints = {
    "current-user": "/user/current-user",
    "login": "/user/login",
    "registerUser": "/user/register",
    "updateUser": "/user/current-user/update",
    "getAllSupplier": "/user/suppliers/",
    "getAllProduct": (userId) => `/user/${userId}/products/`,

    "getAllOrder": '/user/current-user/orders',
    "createOrder": "/orders",
    "orderDetail": (orderId) => `/${orderId}/details`,
}

export const authAPIs = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': `Bearer ${cookie.load('token')}`
        }
    })
}

export default axios.create({
    baseURL: BASE_URL
})