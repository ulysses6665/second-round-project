package oop;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcTest {
    public static void main(String[] args) throws Exception {
        // 读配置
        Properties prop = new Properties();
        try (InputStream in = new FileInputStream("src/db.properties")) {
            prop.load(in);
        }
        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");

        String sql = "SELECT project_name, project_total FROM project";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("project_name")
                        + " 总预算=" + rs.getBigDecimal("project_total"));
            }
        }
    }
}