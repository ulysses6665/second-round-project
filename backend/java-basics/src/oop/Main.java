package oop;

public class Main {
    public static void main(String[] args) {
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

        Member lin = new Member("小林", "男", "霍格沃茨", "10086");
        Project p2 = new Project("传感器采购", 1000);
        Application app = new Application(1, p2, lin, "买传感器", 600);
        System.out.println("提交前的状态是"+app.getStatus().getName());
        System.out.println(p2);

        //提交草稿
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
    }
}
