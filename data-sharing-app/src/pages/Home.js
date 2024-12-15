import React from "react";
import { Link } from "react-router-dom";
import { Button, Typography } from "antd";

const { Title } = Typography;

const Home = () => (
  <div className="home-page">
    <Title>欢迎使用分布式数据系统</Title>
    <div>
      <Link to="/auth">
        <Button type="primary">注册 / 登录</Button>
      </Link>
    </div>
    <div>
      <Link to="/data">
        <Button>查看我的数据</Button>
      </Link>
    </div>
  </div>
);

export default Home;
