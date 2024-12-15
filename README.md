# Distributed Data Exchange and Sharing System

> A VERY SILLY project just for silly Java homework.

Using **Spring Boot** and **React/antd**.

### Usage
###### MySQL
```
-- 用户表
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 数据表
CREATE TABLE user_data(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    userid BIGINT,
    data TEXT,
    Created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userid) REFERENCES users(id) ON DELETE CASCADE
);

```
**Remember to change `application.properties` as your own user.**

###### Spring Boot
Build by **maven**.
I think your IDE will finish itself by reading `pom.xml`.

###### React/antd
STFW

### Finished
Users register and login, data adding and finding.

### TODO
Multi-threading access, data security configuration.