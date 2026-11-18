package oop;

public enum AppStatus {
    //所有状态
    DRAFT("草稿"),
    PENDING_L1("待一级审批"),
    APPROVED("已批准"),
    REJECTED("已拒绝"),
    CANCELED("已取消"),
    SETTLED("已结算");

    private String name;
    private AppStatus(String name){
        this.name =name;
    }
    public String getName(){
        return name;
    }
}
