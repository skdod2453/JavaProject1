package entity;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int likeCount;
    private LocalDateTime createdAt;

    public Post() {}

    public Post(int id, int userId, String title, String content, int likeCount, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
    }

    // get
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public int getLikeCount() { return likeCount; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // set
    public void setId(int id) { this.id = id; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setLikeCount(int likeCount) { this.likeCount = likeCount; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

