package oop;
//申请人的表格
public class Application {
    private int id;//项目名称
    private Project project;
    private Member applicant;//申请人
    private String purpose;//用途
    private int applyAmount;//申请金额
    private int approveAmount;//审批金额
    private int settledAmount;//结算金额
    private AppStatus status;//目前状态(只能有这几种状态，所以利用枚举类中的状态)
    private String reason;//拒绝原因

    //found way
    public Application(int id, Project project, Member applicant, String purpose, int applyAmount) {
        this.id = id;
        this.project = project;
        this.applicant = applicant;
        this.purpose = purpose;
        this.applyAmount = applyAmount;
        this.approveAmount = 0; // 还没批准
        this.settledAmount = 0;      // 还没结算
        this.status = AppStatus.DRAFT;
    }

    public int getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

    public Member getApplicant() {
        return applicant;
    }

    public String getPurpose() {
        return purpose;
    }

    public int getApplyAmount() {
        return applyAmount;
    }

    public int getApproveAmount() {
        return approveAmount;
    }

    public int getSettledAmount() {
        return settledAmount;
    }

    public AppStatus getStatus() {
        return status;
    }

    //submit提交按钮
    public boolean submit() {
        //状态正不正常
        if (status != AppStatus.DRAFT) {
            System.out.println("请在草稿状态下提交");
            return false;
        }
        //用途
        if (purpose == null || purpose.isBlank()) {
            System.out.println("请填写用途");
            return false;
        }
        //申请金额
        if (applyAmount <= 0) {
            System.out.println("申请金额不能小于0");
            return false;
        }
        //经费不能超出可用
        if (applyAmount > project.getAvailable()) {
            System.out.println("申请金额不能超过可用经费");
            return false;
        }
        //提交冻结的钱不能为0
        if (!project.freeze(applyAmount)) {
            System.out.println("提交失败 原因：项目可用经费不足或者申请金额小于0");
            return false;
        }
        status = AppStatus.PENDING_L1;//把状态变成待审批
        return true;
    }


    //toString方法
    @Override
    public String toString() {
        return "#" + id + " " + applicant.getName() + " " + purpose
                + " 申请" + applyAmount + " 批准" + approveAmount
                + " 实花" + settledAmount + " " + status;
    }
}

