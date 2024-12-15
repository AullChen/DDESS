package scnu.cstn2023.DDESS.Service;

import scnu.cstn2023.DDESS.DAO.UserDataMapper;
import scnu.cstn2023.DDESS.Entity.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataService {

    @Autowired
    private UserDataMapper userDataMapper;

    public void uploadData(UserData userData) {
        userDataMapper.insertData(userData);
    }

    public List<UserData> getUserData(Long user_id) {
        return userDataMapper.getDataByUser_id(user_id);
    }

    public List<UserData> getAllData() {
        return userDataMapper.getAllData();
    }

    public void deleteData(Long user_id) {
        userDataMapper.deleteData(user_id);
    }

    public void updateData(Long user_id, String data) {
        userDataMapper.updateData(user_id, data);
    }
}