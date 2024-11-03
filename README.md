# Distributed Data Exchange and Sharing System

> A VERY SILLY project just for silly Java homework.

Using **Spring Boot** and **React/antd**.

### Usage
###### MySQL
```
-- 用户表
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'USER'
);

-- 数据表
CREATE TABLE data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    data_content TEXT,  -- 存储数据内容的字段，可根据需求改为BLOB等类型
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

```
**Remember to change `application.properties` as your own user.**

###### Spring Boot
Build by **maven**.
I think your IDE will finish itself by reading `pom.xml`.

###### React/antd
STFW

### Finished
Users register and login.

### TODO
Connect logic between user and data.
