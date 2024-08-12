import axios from "axios"

const BASE_URL = "http://localhost:8080/nexuschain/api"

export const endpoints = {
    "registerUser": "/suppliers/register"
}

export default axios.create({
    baseURL: BASE_URL
})