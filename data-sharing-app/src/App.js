import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import { Layout } from 'antd';
import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register';
import DataManagement from './components/DataManagement';

const { Header, Content } = Layout;

function App() {
    return (
        <Router>
            <Layout>
                <Header style={{ color: '#fff', textAlign: 'center' }}>数据交换与共享系统</Header>
                <Content style={{ padding: '50px' }}>
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/register" element={<Register />} />
                        <Route path="/data" element={<DataManagement />} />
                    </Routes>
                </Content>
            </Layout>
        </Router>
    );
}

export default App;
