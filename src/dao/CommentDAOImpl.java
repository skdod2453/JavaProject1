package dao;

import entity.Comment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl implements CommentDAO {
    private final Connection conn;

    public CommentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertComment(Comment comment) {
        String sql = "INSERT INTO comments (post_id, user_id, content, created_at) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, comment.getPostId());              // post_id
            pstmt.setInt(2, comment.getUserId());              // user_id
            pstmt.setString(3, comment.getContent());          // content
            pstmt.setTimestamp(4, Timestamp.valueOf(comment.getCreatedAt())); // created_at
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) comment.setId(rs.getInt(1)); // 자동 생성된 ID를 comment 객체에 설정
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comment> getAllComment(int postId) {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE post_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, postId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                comments.add(new Comment(
                        rs.getInt("id"),
                        rs.getInt("post_id"),
                        rs.getInt("user_id"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public void deleteComment(int id) {
        String sql = "DELETE FROM comments WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
