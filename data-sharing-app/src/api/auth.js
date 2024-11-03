import axios from 'axios';

const API_URL = 'http://localhost:8081/api/auth';

export const register = (username, password) => {
    return axios.post(`${API_URL}/register`, { username, password });
};

export const login = (username, password) => {
    return axios.post(`${API_URL}/login`, { username, password })
        .then(response => {
            if (response.data.token) {
                localStorage.setItem('user', JSON.stringify(response.data));
            }
            return response.data;
        });
};

export const logout = () => {
    localStorage.removeItem('user');
};
