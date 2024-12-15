import React, { useEffect, useState } from "react";
import { Table, Button, message } from "antd";
//import axios from "axios";
import { decodeToken, logout } from "../../utils/auth";
import { getUserData, getAllData } from "../../utils/api";

const DataTable = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false);

  // 当前用户信息
  const token = localStorage.getItem("token");
  const currentUser = token ? decodeToken(token) : null;

  // 获取用户自己的数据
  const fetchUserData = async () => {
    setLoading(true);
      try {
        const token = localStorage.getItem("token");
        const { userId } = decodeToken(token); // 解析 token 获取用户 ID
        const response = await getUserData(userId);
        setData(response);
      } catch (err) {
        message.error("获取数据失败");
      } finally {
        setLoading(false);
      }
  };

  // 获取所有数据（管理员权限）
  const fetchAllData = async () => {
    setLoading(true);
      try {
        //const token = localStorage.getItem("token");
        //const { userId } = decodeToken(token); // 解析 token 获取用户 ID
        const response = await getAllData();
        setData(response);
      } catch (err) {
        message.error("获取数据失败");
      } finally {
        setLoading(false);
      }
  };

  // 退出登录
  const handleLogout = () => {
    logout(); // 清除 token
    message.success("已退出登录！");
    window.location.href = "/"; // 跳转到主界面
  };

  // 初始化加载用户数据
  useEffect(() => {
    fetchUserData();
  }, []);

  // 表格列定义
  const columns = [
    {
      title: "ID",
      dataIndex: "id",
      key: "id",
    },
    {
      title: "用户ID",
      dataIndex: "userId",
      key: "userId",
    },
    {
      title: "数据",
      dataIndex: "data",
      key: "data",
    },
  ];

  return (
    <div>
      <h2>数据查看界面</h2>
      <div style={{ marginBottom: 16 }}>
        <Button type="primary" onClick={fetchUserData} loading={loading}>
          刷新我的数据
        </Button>
        <Button type="default" onClick={fetchAllData} disabled={currentUser?.role !== 1} style={{ marginLeft: 8 }}>
          查看所有数据
        </Button>
        <Button type="danger" onClick={handleLogout} style={{ marginLeft: 8 }}>
          退出
        </Button>
      </div>
      <Table
        columns={columns}
        dataSource={data}
        rowKey={(record) => record.id}
        loading={loading}
      />
    </div>
  );
};

export default DataTable;
