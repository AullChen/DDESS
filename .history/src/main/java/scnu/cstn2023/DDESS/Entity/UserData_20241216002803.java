package scnu.cstn2023.DDESS.Entity;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserData {
    private Long id;
    private Long userId;
    private String data;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
