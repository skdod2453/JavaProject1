package dao;

import entity.Comment;

import java.util.List;

public interface CommentDAO {
    void insertCommentDao(Comment comments);   // 댓글 작성
    Comment getCommentById(int id);            // id로 댓글 조회
    List<Comment> getAllComments();            // 모든 댓글 조회
    void updateComments(Comment comments);     // 댓글 수정
    void deleteComments(int id);                // 댓글 삭제
}
