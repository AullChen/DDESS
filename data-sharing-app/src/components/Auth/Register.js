import React, { useState } from "react";
import { Form, Input, Button, message } from "antd";
import { register } from "../../utils/api";

const Register = () => {
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (values) => {
    setLoading(true);
    try {
      await register(values);
      message.success("注册成功");
    } catch (error) {
      message.error("注册失败");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Form onFinish={handleSubmit}>
      <Form.Item name="username" rules={[{ required: true, message: "请输入用户名" }]}>
        <Input placeholder="用户名" />
      </Form.Item>
      <Form.Item name="password" rules={[{ required: true, message: "请输入密码" }]}>
        <Input.Password placeholder="密码" />
      </Form.Item>
      <Button type="primary" htmlType="submit" loading={loading} block>
        注册
      </Button>
    </Form>
  );
};

export default Register;
