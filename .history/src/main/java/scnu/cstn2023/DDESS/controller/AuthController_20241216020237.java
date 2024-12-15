package scnu.cstn2023.DDESS.Controller;


import scnu.cstn2023.DDESS.Service.UserService;
import scnu.cstn2023.DDESS.Auth.JwtTokenProvider;
import scnu.cstn2023.DDESS.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // 用户注册
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("注册成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("注册失败: " + e.getMessage());
        }
    }

    // 用户登录

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User request) {
        try {
            // 从数据库中加载用户信息
            User found = userService.getUserByUsername(request.getUsername());

            // 验证密码
            if (found == null || !request.getPassword().equals(found.getPassword())) {
                throw new BadCredentialsException("Invalid username or password");
            }

            // 生成 JWT 令牌
            String token = jwtTokenProvider.createToken(found.getUsername(), found.getRole(), found.getId());

            // 返回生成的 JWT 令牌
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }
    }

    // 用户注销
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            User found = userService.getUserById(id);
            // 先查找是否存在这个id
            if (found == null) {
                throw new BadCredentialsException("Invalid username or password");
            }
            userService.deleteUser(id);

            return ResponseEntity.ok("注销成功！");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
