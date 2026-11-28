package oop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplicationDao {
    public int insert(Connection conn, int projectId, int memberId,
                      String purpose, int applyAmount) throws SQLException {

        String sql = "INSERT INTO application "
                + "(project_id, member_id, purpose, apply_amount, "
                + " approve_amount, settled_amount, status, reason) "
                + "VALUES (?, ?, ?, ?, 0, 0, 'DRAFT', NULL)";

        // RETURN_GENERATED_KEYS：告诉数据库"我要拿回自增主键"
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, projectId);
            ps.setInt(2, memberId);
            ps.setString(3, purpose);
            ps.setInt(4, applyAmount);

            ps.executeUpdate();

            // 取出数据库分配的 id
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        }
        return -1;
    }
    public boolean updateStatus(Connection conn, int applicationId, String status) throws SQLException {
        String sql = "UPDATE application SET status = ? WHERE application_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, applicationId);
            return ps.executeUpdate() == 1;
        }
    }
}