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
    private String rejectReason;//拒绝的原因
    private String cancelReason;//取消的原因

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
    //审核功能
    //批准功能
    public boolean approve(int approveAmount) {
        //状态是否是待审批
        if (status != AppStatus.PENDING_L1) {
            System.out.println("申请只能在待审批状态审核");
            return false;
        }
        //查看钱合不合理
        if (approveAmount > applyAmount || approveAmount <= 0) {
            System.out.println("批准金额不能大于申请金额或者小于等于0");
            return false;
        }
        project.release(applyAmount - approveAmount);
        status = AppStatus.APPROVED;//把状态变成已批准
        this.approveAmount = approveAmount;
        return true;
    }
    //拒绝功能
    public void setReason_reject(String reason) {
        this.rejectReason = reason;
    }
    public String getReason_reject() {
        return rejectReason;
    }
    public boolean reject(String reason) {
        //状态是否是待审批
        if (status != AppStatus.PENDING_L1) {
            System.out.println("申请只能在待审批状态审核");
            return false;
        }
        //原因不能为空
        if (reason == null || reason.isBlank()) {
            System.out.println("拒绝原因不能为空");
            return false;
        }
        if (!project.release(applyAmount)) {
            System.out.println("拒绝失败：预算回滚异常");
            return false;
        }
        this.rejectReason = reason;
        status = AppStatus.REJECTED;//把状态变成已拒绝
        return true;
    }
//取消按钮
    public void setReason_cancel(String reason) {
        this.cancelReason = reason;
    }
    public String getReason_cancel() {
        return cancelReason;
    }
    public boolean cancel(String reason){
        //状态在待审批或者已批准时才能取消
        if(status != AppStatus.PENDING_L1&&status != AppStatus.APPROVED)
        {
            System.out.println("申请只能在待审批状态或者已批准后取消");
            return false;
        }
        //原因不能为空
        if (reason == null || reason.isBlank()) {
            System.out.println("取消原因不能为空");
            return false;
        }
        //释放剩余冻结的钱：待审批退申请额，已批准退批准额   ！！！ai补充
        int toRelease = (status == AppStatus.APPROVED) ? approveAmount : applyAmount;
        if (toRelease > 0 && !project.release(toRelease)) {
            System.out.println("取消失败：预算回滚异常");
            return false;
        }
        this.cancelReason = reason;
        status = AppStatus.CANCELED;  //暂时不知道什么问题
        return true;
    }
//结算
public boolean settle(int actualAmount) {
        //批准后才能结算
    if (status != AppStatus.APPROVED) {
        System.out.println("只有已批准才能结算");
        return false;
    }
    if (actualAmount < 0 || actualAmount > approveAmount) {
        System.out.println("实际花费必须在 0 到批准金额之间");
        return false;
    }
    if (!project.settle(approveAmount, actualAmount)) {
        System.out.println("结算失败：预算回滚异常");
        return false;
    }
    this.settledAmount = actualAmount;
    status = AppStatus.SETTLED;
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

