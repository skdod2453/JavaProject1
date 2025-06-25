package dao;

import entity.User;

public interface UserDAO {
    void insertUser(User users);       // 회원 추가
    User getUserByNickname(String nickname);
}
