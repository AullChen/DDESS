import React, { useState } from 'react';
import { Form, Input, Button, message } from 'antd';
import { login } from '../api/auth';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const onFinish = async (values) => {
        setLoading(true);
        try {
            await login(values.username, values.password);
            message.success('登录成功');
            navigate('/data');
        } catch (error) {
            message.error('登录失败');
        }
        setLoading(false);
    };

    return (
        <Form onFinish={onFinish}>
            <Form.Item name="username" rules={[{ required: true, message: '请输入用户名' }]}>
                <Input placeholder="用户名" />
            </Form.Item>
            <Form.Item name="password" rules={[{ required: true, message: '请输入密码' }]}>
                <Input.Password placeholder="密码" />
            </Form.Item>
            <Form.Item>
                <Button type="primary" htmlType="submit" loading={loading} block>
                    登录
                </Button>
            </Form.Item>
        </Form>
    );
};

export default Login;
