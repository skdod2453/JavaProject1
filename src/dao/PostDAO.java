package dao;

import entity.Post;

import java.util.List;

public interface PostDAO {
    void insertPost(Post posts);   // 글 작성
    Post getPostById(int id);      // id로 글 조회
    List<Post> getAllPosts();      // 모든 글 조회
    void updatePost(Post posts);   // 글 수정
}
