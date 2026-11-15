package oop;

public class Member {
        //利用面向对象表示成员
       private String name;
       private String gender;
       private String college;
       private String StudenID;
       //构造方法
        public void setName(String name){
                this.name = name;
        }
        public void setGender(String gender){
                this.gender = gender;
        }
        public void setCollege(String college){
                this.college = college;
        }
        public void setStudenID(String StudenID){
                this.StudenID = StudenID;
        }
        //获取成员信息get
        public String getName(){
                return name;
        }
        public String getGender(){
                return gender;
        }
        public String getCollege(){
                return college;
        }
        public String getStudenID(){
                return StudenID;
        }

}
