package com.zx.lab_attendance.entity;

import java.io.Serializable;

public class Permissions implements Serializable {
    private Integer permissionsId;

    private String permissions;

    private String permissionsDes;

    private static final long serialVersionUID = 1L;

    public Integer getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(Integer permissionsId) {
        this.permissionsId = permissionsId;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions == null ? null : permissions.trim();
    }

    public String getPermissionsDes() {
        return permissionsDes;
    }

    public void setPermissionsDes(String permissionsDes) {
        this.permissionsDes = permissionsDes == null ? null : permissionsDes.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", permissionsId=").append(permissionsId);
        sb.append(", permissions=").append(permissions);
        sb.append(", permissionsDes=").append(permissionsDes);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}