package com.job.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/consultant-service")
public class ConsultantApi {

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.status(HttpStatus.OK).body("GET:: consultant controller");
    }
    @PostMapping
    public ResponseEntity<String> post() {
        return ResponseEntity.status(HttpStatus.OK).body("POST:: consultant controller");
    }
    @PutMapping
    public ResponseEntity<String> put() {
        return ResponseEntity.status(HttpStatus.OK).body("PUT:: consultant controller");
    }
    @DeleteMapping
    public ResponseEntity<String> delete() {
        return ResponseEntity.status(HttpStatus.OK).body("DELETE:: consultant controller");
    }
}
