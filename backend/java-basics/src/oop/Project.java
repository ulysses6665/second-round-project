package oop;

import java.awt.print.PrinterAbortException;

public class Project {
    private String name;
    private int total;
    private int available;//一开始全在可用里
    private int forzen;
    private int used;

    public Project(String name, int total) {
        if (total <= 0) {
            System.out.println("预算你要小于零？我给你点天地银行的要不要");
            return;
        }
        this.name = name;
        //初始的钱
        this.total = total;
        this.available = total;
        this.forzen = 0;
        this.used = 0;
    }

    //1.冻结；提交时使用
    //用boolean来确定目前申请的冻结是否成功
    public boolean freeze(int amount) {
        if (amount <= 0) {
            System.out.println("失败");
            return false;
        }
        if (amount > available) {
            System.out.println("失败");
            return false;
        }
        //冻结的是项目请求的
        //现在的可用是留给别的项目的
        available = available - amount;
        //后来冻结的是前面冻结的加上现在申请的
        forzen = forzen + amount;
        return true;
    }

    //2.释放
    //还回来的钱（不给用的）
    public boolean release(int amount) {
        if (amount <= 0) {
            System.out.println("失败");
            return false;
        }
        //拿回来的钱不能大于目前冻结的
        if (amount > forzen) {
            System.out.println("失败 （好无奈）");
            return false;
        }
        forzen = forzen - amount;
        available = available + amount;
        return true;
    }

    //3.结算 财务登记实际用了多少钱
    //同意给的和实际用到的
    public boolean settle(int ApproveAmount, int ActualAmount) {
        if (ApproveAmount <= 0 || ActualAmount > ApproveAmount) {
            System.out.println("批准金额不合法");
            return false;
        }
        if (ActualAmount < 0 || ActualAmount > ApproveAmount) {
            System.out.println("实际花费必须在 0 到批准金额之间");
            return false;
        }
        forzen = forzen - ApproveAmount;
        used = used + ActualAmount;
        available = available + (ApproveAmount - ActualAmount);
        return true;
    }

    //4.检查一下钱守恒
    public boolean check() {
        return total == available + forzen + used && available >= 0 && forzen >= 0 && used >= 0;
    }
    //5.打印检查
    @Override
    public String toString(){
        return "["+name+"] 可用="+available
                +" 冻结="+forzen+" 已使用="+used
                + " 校验=" + (check() ? "通过" : "失败");//三目运算输出有没有问题
    }
}
