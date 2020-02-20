package com.zx.lab_attendance.entity;

import java.io.Serializable;
import java.util.Date;

public class Applylab implements Serializable {
    private String applylabId;

    private String applyUserid;

    private String laboratoryNumber;

    private String using;

    private String approver;

    private Date usingStart;

    private Date usingEnd;

    private static final long serialVersionUID = 1L;

    public String getApplylabId() {
        return applylabId;
    }

    public void setApplylabId(String applylabId) {
        this.applylabId = applylabId == null ? null : applylabId.trim();
    }

    public String getApplyUserid() {
        return applyUserid;
    }

    public void setApplyUserid(String applyUserid) {
        this.applyUserid = applyUserid == null ? null : applyUserid.trim();
    }

    public String getLaboratoryNumber() {
        return laboratoryNumber;
    }

    public void setLaboratoryNumber(String laboratoryNumber) {
        this.laboratoryNumber = laboratoryNumber == null ? null : laboratoryNumber.trim();
    }

    public String getUsing() {
        return using;
    }

    public void setUsing(String using) {
        this.using = using == null ? null : using.trim();
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    public Date getUsingStart() {
        return usingStart;
    }

    public void setUsingStart(Date usingStart) {
        this.usingStart = usingStart;
    }

    public Date getUsingEnd() {
        return usingEnd;
    }

    public void setUsingEnd(Date usingEnd) {
        this.usingEnd = usingEnd;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", applylabId=").append(applylabId);
        sb.append(", applyUserid=").append(applyUserid);
        sb.append(", laboratoryNumber=").append(laboratoryNumber);
        sb.append(", using=").append(using);
        sb.append(", approver=").append(approver);
        sb.append(", usingStart=").append(usingStart);
        sb.append(", usingEnd=").append(usingEnd);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}