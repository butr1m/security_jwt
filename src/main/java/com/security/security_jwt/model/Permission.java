package com.security.security_jwt.model;


public enum Permission {
    DEVELOPER_READ("developer:read"),
    DEVELOPER_WRITE("developer:write");

    private final String permissions;

    Permission(String permissions) {
        this.permissions = permissions;
    }

    public String getPermissions() {
        return permissions;
    }
}
