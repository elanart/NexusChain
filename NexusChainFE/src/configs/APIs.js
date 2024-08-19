import axios from "axios"
import cookie from "react-cookies";

const BASE_URL = "http://localhost:8080/nexuschain/api"

export const endpoints = {
    "registerUser": "/user/register",
    "login": "/user/login",
    "current-user": "/user/current-user"
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