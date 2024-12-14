package scnu.cstn2023.DDESS.Controller;

import scnu.cstn2023.DDESS.Auth.JwtTokenProvider;
import scnu.cstn2023.DDESS.entity.UserData;
import com.example.dataexchange.Service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // 上传数据
    @PostMapping("/upload")
    public String uploadData(@RequestBody UserData userData) {
        userDataService.uploadData(userData);
        return "Data uploaded successfully!";
    }

    // 查询用户数据（普通用户）
    @GetMapping("/mydata/{user_id}")
    public List<UserData> getUserData(@PathVariable Long user_id) {
        return userDataService.getUserData(user_id);
    }

    // 管理员查询所有数据
    @GetMapping("/alldata")
    public List<UserData> getAllData(@RequestHeader("Authorization") String token) {
        try {
            String username = jwtTokenProvider.getUsernameFromToken(token.substring(7)); // 去掉 "Bearer "
            int role = jwtTokenProvider.getRoleFromToken(token.substring(7)); // 获取角色信息
            if (role != 1) {
                // 管理员可以查看所有数据
                throw new BadCredentialsException("不是管理员，不能查看所有人信息！");
            }

            else {
                return userDataService.getAllData();
            }
        } catch (BadCredentialsException e) {
            return (List<UserData>) ResponseEntity.status(404).body(new HashMap<>());
        }
    }

    // 更新数据

    @PutMapping("/update/{id}")
    public String updateData(@PathVariable Long id, @RequestBody String data) {
        userDataService.updateData(id, data);
        return "Data updated successfully!";
    }

    // 删除数据
    @DeleteMapping("/delete/{id}")
    public String deleteData(@PathVariable Long id) {
        userDataService.deleteData(id);
        return "Data deleted successfully!";
    }

}