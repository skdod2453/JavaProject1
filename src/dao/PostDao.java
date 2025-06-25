package dao;

import entity.Posts;

import java.util.List;

public interface PostDao {
    void insertPost(Posts posts);   // 글 작성
    Posts getPostById(int id);      // id로 글 조회
    List<Posts> getAllPosts();      // 모든 글 조회
    void updatePost(Posts posts);   // 글 수정
    void deletePost(int id);        // id로 글 삭제
}
