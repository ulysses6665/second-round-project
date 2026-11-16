package oop;

public class main {
    static void main() {
        Project p = new Project("传感器采购", 1000);
        System.out.println(p);          // 初始

        p.freeze(600);                  // 提交 600
        System.out.println(p);

        p.release(100);                 // 批准 500（600 里多的 100 还回去）
        System.out.println(p);

        p.settle(500, 460);             // 结算：批 500，实花 460
        System.out.println(p);
    }
}
