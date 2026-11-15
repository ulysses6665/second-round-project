package oop;

public class member1 {
    static void main() {
        //创建对象记录第一个成员的信息
        Member member1 = new Member();
        member1.name = "小林";
        member1.gender = "男";
        member1.college = "霍格沃兹学院";
        member1.StudenID = "10086";

        //查看信息
        System.out.println(member1.name);
        System.out.println(member1.gender);
        System.out.println(member1.college);
        System.out.println(member1.StudenID);
    }
}
