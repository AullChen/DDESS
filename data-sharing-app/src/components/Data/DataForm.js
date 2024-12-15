import React from "react";
import { Form, Input, Button, message } from "antd";
import { uploadData } from "../../utils/api";
import { decodeToken } from "../../utils/auth";

const DataForm = ({ onSuccess }) => {
  const onFinish = async (values) => {
    try {
      const token = localStorage.getItem("token");
      const { userId } = decodeToken(token); // 获取用户 ID
      await uploadData({ ...values, userId });
      message.success("数据上传成功！");
      onSuccess(); // 上传后刷新数据
    } catch (err) {
      message.error("上传失败");
    }
  };

  return (
    <Form onFinish={onFinish}>
      <Form.Item name="data" rules={[{ required: true, message: "请输入数据" }]}>
        <Input placeholder="数据" />
      </Form.Item>
      <Button type="primary" htmlType="submit">
        上传
      </Button>
    </Form>
  );
};

export default DataForm;
