package scnu.cstn2023.DDESS.Service;

import scnu.cstn2023.DDESS.DAO.UserMapper;
import scnu.cstn2023.DDESS.Entity.User;
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