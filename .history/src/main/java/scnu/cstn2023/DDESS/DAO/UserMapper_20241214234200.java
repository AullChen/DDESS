package scnu.cstn2023.DDESS.DAO;

import com.example.dataexchange.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users(username, password, role) VALUES(#{username}, #{password}, #{role})")
    void insertUser(User user);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(Long id);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteById(Long id);
}
