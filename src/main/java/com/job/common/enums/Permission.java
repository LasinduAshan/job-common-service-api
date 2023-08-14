package com.job.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    CONSULTANT_READ("management:read"),
    CONSULTANT_UPDATE("management:update"),
    CONSULTANT_CREATE("management:create"),
    CONSULTANT_DELETE("management:delete");

    @Getter
    private final String permission;
}
