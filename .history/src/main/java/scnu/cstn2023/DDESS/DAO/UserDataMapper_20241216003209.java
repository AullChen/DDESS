package scnu.cstn2023.DDESS.DAO;

import scnu.cstn2023.DDESS.Entity.UserData;
import org.apache.ibatis.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Mapper
public interface UserDataMapper {

    @Insert("INSERT INTO user_data (userid, data) VALUES (#{userId}, #{data})")
    void insertData(UserData userData);

    @Select("SELECT * FROM user_data WHERE userid = #{user_id}")
    List<UserData> getDataByUser_id(Long user_id);

    @Select("SELECT * FROM user_data")
    List<UserData> getAllData();

    @Delete("DELETE FROM user_data WHERE id=#{id}")
    void deleteData(Long id);

    @Update("UPDATE user_data SET data = #{data} WHERE userid=#{id}")
    void updateData(Long id, String data);
}
