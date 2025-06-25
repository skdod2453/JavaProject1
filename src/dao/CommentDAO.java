package dao;

import entity.Comments;

import java.util.List;

public interface CommentDAO {
    void insertCommentDao(Comments comments);   // 댓글 작성
    Comments getCommentById(int id);            // id로 댓글 조회
    List<Comments> getAllComments();            // 모든 댓글 조회
    void updateComments(Comments comments);     // 댓글 수정
    void deleteComments(int id);                // 댓글 삭제
}
