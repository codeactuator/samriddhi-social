package com.codeactuator.samriddhi.constants;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.codeactuator.samriddhi.constants.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    USER(Sets.newHashSet(POST_READ, POST_WRITE, COMMENT_READ, COMMENT_WRITE)),
    ADMIN(Sets.newHashSet(POST_READ, POST_WRITE, COMMENT_READ, COMMENT_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
