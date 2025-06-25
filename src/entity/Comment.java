package entity;

import java.util.Date;

public class Comment {
    private int id;
    private int postId;
    private int userId;
    private String content;
    private Date createdAt;

    public Comment() {
    }

    public Comment(int id, int postId, int userId, String content, Date createdAt) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
    }

    // get
    public int getId() { return id; }
    public int getPostId() { return postId; }
    public int getUserId() { return userId; }
    public String getContent() { return content; }
    public Date getCreatedAt() { return createdAt; }

    // set
    public void setId(int id) { this.id = id; }
    public void setPostId(int postId) { this.postId = postId; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setContent(String content) { this.content = content; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt;}
}
