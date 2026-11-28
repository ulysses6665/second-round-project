package oop;

import org.junit.Test;

import java.sql.Connection;

public class DaoTest {
    public static void main(String[] args) throws Exception {
        try (Connection conn = JdbcUtil.getConnection()) {
            ProjectDao dao = new ProjectDao();

            System.out.println("改前: " + dao.findById(conn, 1));

            // 模拟提交 600：可用 -600，冻结 +600
            boolean ok = dao.updateMoney(conn, 1, -600, +600);
            System.out.println("改金额成功=" + ok);

            System.out.println("改后: " + dao.findById(conn, 1));

            // 还原，别把数据改坏
            dao.updateMoney(conn, 1, +600, -600);
            System.out.println("还原: " + dao.findById(conn, 1));
            //applicantion测试
            ApplicationDao appDao = new ApplicationDao();

            int newId = appDao.insert(conn, 1, 1, "买传感器测试", 300);
            System.out.println("新申请 id = " + newId);

            System.out.println("改状态结果 = " + appDao.updateStatus(conn, newId, "PENDING_L1"));
        }
    }
}

