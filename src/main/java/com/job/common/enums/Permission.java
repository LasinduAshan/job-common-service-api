package com.job.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    CONSULTANT_READ("consultant:read"),
    CONSULTANT_UPDATE("consultant:update"),
    CONSULTANT_CREATE("consultant:create"),
    CONSULTANT_DELETE("consultant:delete");

    @Getter
    private final String permission;
}
