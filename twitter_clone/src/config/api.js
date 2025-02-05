import axios from "axios"

export const API_BASE_URL="http://localhost:8080"

export const api=axios.create({
    baseURL:API_BASE_URL,
    headers:{
        "Authorization":`Bearer ${localStorage.getItem("jwt")}`,
        "Content-Type":"application/json"
    }
})

api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("jwt"); // Lấy token mới nhất
        if (token) {
            config.headers["Authorization"] = `Bearer ${token}`;
        } else {
            delete config.headers["Authorization"]; // Xóa nếu không có token
        }
        return config;
    },
    (error) => Promise.reject(error)
);
