package dao;

import entity.Users;

import java.util.List;

public interface UserDao {
    void insertUser(Users users);
    Users getUserById(int id);
    List<Users> getAllUsers();
    void updateUser(Users users);
    void deleteUser(int id);
}
