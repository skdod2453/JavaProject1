package dao;

import entity.User;
import java.util.List;

public interface UserDAO {
    void insertUser(User users);       // 회원 추가
    User getUserById(int id);          // id로 회원 조회
    List<User> getAllUsers();          // 모든 회원 조회
    void updateUser(User users);       // 회원 수정
    void deleteUser(int id);            // 회원 삭제
}
