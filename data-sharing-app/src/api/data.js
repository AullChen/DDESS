import axios from 'axios';

const API_URL = 'http://localhost:8081/api/data';

const authHeader = () => {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user && user.token) {
        return { Authorization: `Bearer ${user.token}` };
    } else {
        return {};
    }
};

export const createData = (dataContent) => {
    return axios.post(`${API_URL}/create`, { dataContent }, { headers: authHeader() });
};

export const getData = () => {
    return axios.get(API_URL, { headers: authHeader() });
};

export const updateData = (id, newDataContent) => {
    return axios.put(`${API_URL}/update/${id}`, { dataContent: newDataContent }, { headers: authHeader() });
};

export const deleteData = (id) => {
    return axios.delete(`${API_URL}/delete/${id}`, { headers: authHeader() });
};
