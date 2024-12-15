import React, { useState } from "react";
import Login from "../components/Auth/Login";
import Register from "../components/Auth/Register";
import { Tabs } from "antd";

const AuthPage = () => {
  const [activeTab, setActiveTab] = useState("login");

  return (
    <Tabs
      defaultActiveKey={activeTab}
      onChange={(key) => setActiveTab(key)}
      centered
    >
      <Tabs.TabPane tab="登录" key="login">
        <Login />
      </Tabs.TabPane>
      <Tabs.TabPane tab="注册" key="register">
        <Register />
      </Tabs.TabPane>
    </Tabs>
  );
};

export default AuthPage;
