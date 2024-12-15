import axios from "axios";
import { message } from "antd";

const BASE_URL = "http://localhost:8081/api";

export const login = async (credentials) => {
  const response = await axios.post(`${BASE_URL}/auth/login`, credentials);
  return response.data;
};

export const register = async (userData) => {
  await axios.post(`${BASE_URL}/auth/register`, userData);
};

export const uploadData = async (data) => {
  const token = localStorage.getItem("token");
  await axios.post(`${BASE_URL}/upload`, data, {
    headers: { Authorization: `Bearer ${token}` },
  });
};

export const getUserData = async () => {
  const token = localStorage.getItem("token");
  const response = await axios.get(`${BASE_URL}/mydata`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

export const logout = () => {
  localStorage.removeItem("token");
  message.success("已退出登录！");
  window.location.href = "/"; // 跳转到主界面
};

export const getAllData = async () => {
  try {
    const token = localStorage.getItem("token");
	const response = await axios.get(`${BASE_URL}/alldata`, {
    headers: { Authorization: `Bearer ${token}` },
	});
	return response.data;
  } catch (error) {
    message.error("加载所有数据失败：" + error.response?.data || "未知错误");
    return [];
  }
};