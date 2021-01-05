package com.codeactuator.samriddhi.constants;

public enum ApplicationUserPermission {

    POST_READ("post:read"),
    POST_WRITE("post:write"),
    COMMENT_READ("comment:read"),
    COMMENT_WRITE("comment:write");

    private final String permission;

    ApplicationUserPermission(String s) {
        this.permission = s;
    }

    public String getPermission() {
        return permission;
    }
}
