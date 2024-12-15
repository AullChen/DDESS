import { jwtDecode } from "jwt-decode";

/**
 * 解析 JWT token
 * @param {string} token - JWT token
 * @returns {object} - 解析后的 token 数据
 */
export const decodeToken = (token) => {
  if (!token) {
    throw new Error("Token is not provided");
  }
  try {
    return jwtDecode(token); // 使用命名导出的 jwtDecode
  } catch (err) {
    throw new Error("Invalid token");
  }
};

/**
 * 检查用户是否已登录
 * @returns {boolean} - 用户是否已登录
 */
export const isAuthenticated = () => {
  const token = localStorage.getItem("token");
  if (!token) {
    return false;
  }

  try {
    const decoded = decodeToken(token);
    // 检查 token 是否过期
    const currentTime = Date.now() / 1000;
    return decoded.exp > currentTime;
  } catch (err) {
    return false;
  }
};

/**
 * 注销用户，清除本地存储的 token
 */
export const logout = () => {
  localStorage.removeItem("token");
};
