package com.zx.lab_attendance.entity;

import java.io.Serializable;

public class Laboratory implements Serializable {
    private String laboratoryId;

    private String laboratoryNumber;

    private Integer capacity;

    private Integer machinesign;

    private static final long serialVersionUID = 1L;

    public String getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(String laboratoryId) {
        this.laboratoryId = laboratoryId == null ? null : laboratoryId.trim();
    }

    public String getLaboratoryNumber() {
        return laboratoryNumber;
    }

    public void setLaboratoryNumber(String laboratoryNumber) {
        this.laboratoryNumber = laboratoryNumber == null ? null : laboratoryNumber.trim();
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getMachinesign() {
        return machinesign;
    }

    public void setMachinesign(Integer machinesign) {
        this.machinesign = machinesign;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", laboratoryId=").append(laboratoryId);
        sb.append(", laboratoryNumber=").append(laboratoryNumber);
        sb.append(", capacity=").append(capacity);
        sb.append(", machinesign=").append(machinesign);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}