package scnu.cstn2023.DDESS.model;

import javarta.persistence.*;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_content", nullable = false)
    private String dataContent;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setDataContent(String dataContent2) {
        this.dataContent = dataContent2;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    public void setUser(User user2) {
        this.user = user2;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }
    
}