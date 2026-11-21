package oop;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        sence_normal();
//        sence_reject();
        sence_cancel1();
        sence_reject();
    }
/*
        Project p = new Project("传感器采购", 1000);
        System.out.println(p);          // 初始

        p.freeze(600);                  // 提交 600
        System.out.println(p);

        p.release(100);                 // 批准 500（600 里多的 100 还回去）
        System.out.println(p);

        p.settle(500, 460);             // 结算：批 500，实花 460
        System.out.println(p);
*/
//public Member(String name,String gender,String college,String StudenID)
//    public Application(int id, Project project, Member applicant, String purpose, int applyAmount)
//    public Project(String name, int total)
//    DRAFT("草稿"),
//    PENDING_L1("待一级审批"),
//    APPROVED("已批准"),
//    REJECTED("已拒绝"),
//    CANCELED("已取消"),
//    SETTLED("已结算");


    //正常场景
    static void sence_normal() {
        System.out.println("2.正常场景");
        Member lin = new Member("小林", "男", "霍格沃茨", "10086");
        Project p1 = new Project("传感器采购", 1000);
        Application app1 = new Application(1, p1, lin, "买传感器", 600);
        System.out.println("目前状态是" + app1.getStatus().getName());
        System.out.println(p1);
        //提交一次
        if (app1.submit()) {
            System.out.println("提交成功，状态=" + app1.getStatus().getName());
        } else {
            System.out.println("提交失败，状态保持=" + app1.getStatus().getName());
        }
        System.out.println("目前状态是" + app1.getStatus().getName());
        System.out.println(p1);
        //展示背景
        System.out.println("申请人为："+lin.getName()+",性别"+lin.getGender()+"，就读于"+lin.getCollege()+",学号是"+lin.getStudenID());
        System.out.println("申请金额为："+app1.getApplyAmount());
        //批准
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入批准金额：");
        int approveAmount = sc.nextInt();
        if(app1.approve(approveAmount))
        {
            System.out.println("批准成功，状态=" + app1.getStatus().getName());
        }
        System.out.println(p1);
    }
    //取消场景

    static void sence_cancel1() {
        System.out.println("2.取消场景");
        Member tafei = new Member("永雏塔菲", "沃尔玛购物袋", "史莱克学院", "2778");
        Project p2 = new Project("解救同胞", 1000);
        Application app2 = new Application(2, p2, tafei, "解救同胞", 600);
        Scanner sc = new Scanner(System.in);

        System.out.println(p2);
        if (app2.submit()) {
            System.out.println("提交成功，状态=" + app2.getStatus().getName());
        } else {
            System.out.println("提交失败，状态保持=" + app2.getStatus().getName());
        }
        System.out.println("目前状态是" + app2.getStatus().getName());
        System.out.println(p2);
        //展示背景
        System.out.println("申请人为："+tafei.getName()+",性别"+tafei.getGender()+"，就读于"+tafei.getCollege()+",学号是"+tafei.getStudenID());
        System.out.println("申请金额为："+app2.getApplyAmount());
        //批准
        System.out.print("请输入批准金额：");
        int approveAmount = sc.nextInt();
        sc.nextLine();//吃掉缓存区的回车  ！！!ai补充
        if(app2.approve(approveAmount)){
            System.out.println("批准成功，状态=" + app2.getStatus().getName());
        }
        else {
            System.out.println("批准失败，状态保持=" + app2.getStatus().getName());
        }
        System.out.println(p2);
        //取消
        System.out.print("请输入取消原因：");
        app2.setReason_cancel(sc.nextLine());//填写取消原因
        if (app2.cancel(app2.getReason_cancel())) {
            System.out.println("取消成功，状态=" + app2.getStatus().getName()+"您填写的提交原因是"+app2.getReason_cancel());
        } else {
            System.out.println("取消失败，状态保持=" + app2.getStatus().getName());
        }
        System.out.println(p2);
    }

    //拒绝测试
    static void sence_reject() {
        System.out.println("3.拒绝场景测试");
        Member xm = new Member("马嘉祺", "未知", "时代少年团", "91");
        Project p3 = new Project("购买一些白袜", 1000);
        Application app3 = new Application(3, p3, xm, "购买一些白袜", 600);
        System.out.println("目前状态是" + app3.getStatus().getName());
        System.out.println(p3);
        //展示背景
        System.out.println("申请人为："+xm.getName()+",性别"+xm.getGender()+"就读于"+xm.getCollege()+",学号是"+xm.getStudenID());
        System.out.println("申请金额为："+app3.getApplyAmount());
        if(app3.submit()){
            System.out.println("提交成功，状态=" + app3.getStatus().getName());
        } else {
            System.out.println("提交失败，状态保持=" + app3.getStatus().getName());
        }
        System.out.println(p3);
        //reject
        System.out.println("请输入拒绝原因");
        Scanner sc = new Scanner(System.in);
        app3.setReason_reject(sc.nextLine());
        String reason = app3.getReason_reject();
        if(app3.reject(app3.getReason_reject()))
        {
            System.out.println("拒绝成功，状态=" + app3.getStatus().getName());
            System.out.println("您填写的拒绝原因是" + app3.getReason_reject());
        }
        System.out.println(p3);
    }


//        Member lin = new Member("小林", "男", "霍格沃茨", "10086");
//        Project p2 = new Project("传感器采购", 1000);
//        Application app = new Application(1, p2, lin, "买传感器", 600);
//        System.out.println("提交前的状态是"+app.getStatus().getName());
//        System.out.println(p2);
































     /*   //提交草稿
        app.submit();
        System.out.println("提交后的状态是"+app.getStatus().getName());
        System.out.println(p2);
        //测试再次提交有没有问题
        if (app.submit()) {
            System.out.println("提交成功，状态=" + app.getStatus().getName());
        } else {
            System.out.println("提交失败，状态保持=" + app.getStatus().getName());
        }
        System.out.println(p2);
    }*/
    }

