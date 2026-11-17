package oop;

public class Member {
        //利用面向对象表示成员
       private String name;
       private String gender;
       private String college;
       private String StudenID;
       private String role;
       //构造方法
    //不带参数的构造方法
    public Member(){
    }
    //带全部参数的构造方法
    public Member(String name,String gender,String college,String StudenID){
        this.name = name;
        this.gender = gender;
        this.college = college;
        this.StudenID = StudenID;
        this.role = "member";
    }

    //set成员信息
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
        public void setRole(String role){
                this.role = role;
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
        public String getRole(){
                return role;
        }

}
