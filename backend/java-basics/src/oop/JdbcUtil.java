package oop;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    // 静态代码块：类第一次被使用时执行一次，只读一次配置文件
    static {
        Properties prop = new Properties();
        try (InputStream in = new FileInputStream("src/db.properties")) {
            prop.load(in);
        } catch (Exception e) {
            throw new ExceptionInInitializerError("读取 db.properties 失败：" + e.getMessage());
        }
        URL = prop.getProperty("url");
        USER = prop.getProperty("user");
        PASSWORD = prop.getProperty("password");
    }

    // 拿连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}