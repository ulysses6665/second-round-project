package oop;

public class member1 {
    static void main() {
        //创建对象记录第一个成员的信息
        Member member1 = new Member();
        member1.setName("小林");
        member1.setGender("男");
        member1.setCollege("霍格沃兹学院");
        member1.setStudenID("10086");

        //查看信息
        System.out.println(member1.getName());
        System.out.println(member1.getGender());
        System.out.println(member1.getCollege());
        System.out.println(member1.getStudenID());
    }
}
