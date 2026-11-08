public class BudgetDemo {

    public static void main(String[] args) {
        int total = 1000;      // 项目总预算
        int available = 1000;  // 可用
        int frozen = 0;        // 冻结
        int used = 0;          // 已使用

        System.out.println("=== 初始 ===");
        printState("初始", total, available, frozen, used);

        // 第 1 步：小林申请 600 元并提交
        int applyAmount = 600;
        if (applyAmount > 0 && available >= applyAmount) {
            available = available - applyAmount;
            frozen = frozen + applyAmount;
            printState("提交申请 " + applyAmount, total, available, frozen, used);
        } else {
            System.out.println("提交失败：金额不合法或经费不足，仍为草稿");
        }

        // 第 2 步：老师批准 500 元，少的 100 元恢复可用
        int approved = 500;
        if (approved > 0 && approved <= applyAmount) {
            int back = applyAmount - approved;
            frozen = frozen - back;
            available = available + back;
            printState("批准 " + approved, total, available, frozen, used);
        } else {
            System.out.println("批准失败：批准金额必须在 1 到 " + applyAmount + " 之间");
        }

        // 第 3 步：财务登记实际花费 460 元，剩下的冻结也恢复
        int actual = 460;
        if (actual >= 0 && actual <= approved) {
            frozen = frozen - approved;
            used = used + actual;
            available = available + (approved - actual);
            printState("结算 " + actual, total, available, frozen, used);
        } else {
            System.out.println("结算失败：实际花费必须在 0 到 " + approved + " 之间");
        }
    }

    // 每做一步都打印三项金额，并检查等式是否成立
    static void printState(String step, int total, int available, int frozen, int used) {
        boolean ok = (available + frozen + used == total)
                && available >= 0 && frozen >= 0 && used >= 0;
        System.out.println(step + " -> 可用=" + available
                + " 冻结=" + frozen + " 已使用=" + used
                + "  校验=" + (ok ? "通过" : "失败"));
    }
}