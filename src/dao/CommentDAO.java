package dao;

import entity.Comment;

import java.util.List;

public interface CommentDAO {
    void insertComment(Comment comments);   // 댓글 작성
    List<Comment> getAllComment(int postId);            // 모든 댓글 조회
    void deleteComment(int id);                // 댓글 삭제
}
