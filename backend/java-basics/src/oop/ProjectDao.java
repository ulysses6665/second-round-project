package oop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectDao {

    public Project findById(Connection conn, int projectId) throws SQLException {
        String sql = "SELECT project_id, project_name, project_total, "
                + "project_available, project_frozen, project_used "
                + "FROM project WHERE project_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, projectId);                      // ← 关键：设置参数

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Project(
                            rs.getInt("project_id"),
                            rs.getString("project_name"),
                            rs.getInt("project_total"),
                            rs.getInt("project_available"),
                            rs.getInt("project_frozen"),
                            rs.getInt("project_used")
                    );
                }
                return null;   // 查不到返回 null
            }
        }
    }
    public boolean updateMoney(Connection conn, int projectId,
                               int availableDelta, int frozenDelta) throws SQLException {
        String sql = "UPDATE project SET "
                + "project_available = project_available + ?, "
                + "project_frozen    = project_frozen + ? "
                + "WHERE project_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, availableDelta);
            ps.setInt(2, frozenDelta);
            ps.setInt(3, projectId);
            return ps.executeUpdate() == 1;
        }
    }
}