package scnu.cstn2023.DDESS.Service;

import com.example.dataexchange.Dao.UserMapper;
import com.example.dataexchange.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void registerUser(User user) {
        userMapper.insertUser(user);
    }

    public User getUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public User getUserById(Long id) {
        return userMapper.findById(id);
    }

    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }
}