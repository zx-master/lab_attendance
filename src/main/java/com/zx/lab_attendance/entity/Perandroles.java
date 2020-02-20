package com.zx.lab_attendance.entity;

import java.io.Serializable;

public class Perandroles implements Serializable {
    private Integer perandrolesId;

    private Integer permissionsId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;

    public Integer getPerandrolesId() {
        return perandrolesId;
    }

    public void setPerandrolesId(Integer perandrolesId) {
        this.perandrolesId = perandrolesId;
    }

    public Integer getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(Integer permissionsId) {
        this.permissionsId = permissionsId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", perandrolesId=").append(perandrolesId);
        sb.append(", permissionsId=").append(permissionsId);
        sb.append(", roleId=").append(roleId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}