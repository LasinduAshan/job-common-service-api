package com.job.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin-service")
@PreAuthorize("hasRole('ADMIN')")
public class AdminApi {

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<String> get() {
        return ResponseEntity.status(HttpStatus.OK).body( "GET:: admin controller");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<String> post() {
        return ResponseEntity.status(HttpStatus.OK).body( "POST:: admin controller");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<String> put() {
        return ResponseEntity.status(HttpStatus.OK).body( "PUT:: admin controller");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<String> delete() {
        return ResponseEntity.status(HttpStatus.OK).body( "DELETE:: admin controller");
    }
}
