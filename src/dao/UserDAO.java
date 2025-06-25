package dao;

import entity.Users;

import java.util.List;

public interface UserDAO {
    void insertUser(Users users);       // 회원 추가
    Users getUserById(int id);          // id로 회원 조회
    List<Users> getAllUsers();          // 모든 회원 조회
    void updateUser(Users users);       // 회원 수정
    void deleteUser(int id);            // 회원 삭제
}
