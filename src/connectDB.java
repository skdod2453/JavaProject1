import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class connectDB {
    public static Connection getConnection() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("db.properties"));  // 같은 경로 또는 classpath에 있어야 함
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            return DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            System.out.println("⚠️ DB 설정 파일을 읽는 데 실패했습니다.");
        } catch (SQLException e) {
            System.out.println("❌ DB 연결 실패");
            e.printStackTrace();
        }
        return null;
    }
}