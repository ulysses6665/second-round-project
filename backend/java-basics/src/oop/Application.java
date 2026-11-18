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
   this.id=id;
   this.project=project;
   this.applicant=applicant;
   this.purpose=purpose;
   this.applyAmount=applyAmount;
   this.approveAmount = 0; // 还没批准
        this.settledAmount = 0;      // 还没结算
        this.status = AppStatus.DRAFT;
    }

    public int getId() {return id;}
    public Project getProject() {return project;}
    public Member getApplicant() {return applicant;}
    public String getPurpose() {return purpose;}
    public int getApplyAmount() {return applyAmount;}
    public int getApproveAmount() {return approveAmount;}
    public int getSettledAmount() { return settledAmount; }
    public AppStatus getStatus() {return status;}
//toString方法
    @Override
    public String toString() {
        return "#" + id + " " + applicant.getName() + " " + purpose
                + " 申请" + applyAmount + " 批准" + approveAmount
                + " 实花" + settledAmount + " " + status;
    }
}
