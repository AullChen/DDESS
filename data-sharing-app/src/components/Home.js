// src/components/Home.js

import React from 'react';
import { Button, Space, Typography } from 'antd';
import { useNavigate } from 'react-router-dom';

const { Title } = Typography;

function Home() {
    const navigate = useNavigate();

    return (
        <div style={{ textAlign: 'center', padding: '50px' }}>
            <Title level={2}>欢迎来到数据交换与共享系统</Title>
            <p>请选择登录或注册以继续：</p>
            <Space>
                <Button type="primary" onClick={() => navigate('/login')}>
                    登录
                </Button>
                <Button onClick={() => navigate('/register')}>
                    注册
                </Button>
            </Space>
        </div>
    );
}

export default Home;
