package oop;

public class member1 {
    static void main() {
        //创建对象记录第一个成员的信息
       Member member1 = new Member("小林","男","霍格沃兹学院","10086");

        //查看信息
        System.out.println(member1.getName());
        System.out.println(member1.getGender());
        System.out.println(member1.getCollege());
        System.out.println(member1.getStudenID());
    }
}
