import React from 'react';
import { Form, Input, Button, message } from 'antd';
import { register } from '../api/auth';

const Register = () => {
    const onFinish = async (values) => {
        try {
            await register(values.username, values.password);
            message.success('注册成功');
        } catch (error) {
            message.error('注册失败');
        }
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
                <Button type="primary" htmlType="submit" block>
                    注册
                </Button>
            </Form.Item>
        </Form>
    );
};

export default Register;
