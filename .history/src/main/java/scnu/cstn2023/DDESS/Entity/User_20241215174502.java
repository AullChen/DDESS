package scnu.cstn2023.DDESS.Entity;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private int role; // 0: 普通用户, 1: 管理员

}
