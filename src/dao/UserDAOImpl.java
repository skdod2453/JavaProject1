package dao;

import entity.User;
import java.sql.*;

public class UserDAOImpl implements UserDAO {
    private final Connection conn;

    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertUser(User user) {
        String sql = "INSERT INTO users (nickname) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getNickname());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) user.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByNickname(String nickname) {
        String sql = "SELECT * FROM users WHERE nickname = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nickname);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("nickname")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}