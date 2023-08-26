package com.job.common.controller;

import com.job.common.dto.ConsultantDto;
import com.job.common.service.ConsultantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consultant-service")
@RequiredArgsConstructor
public class ConsultantApi {

    private final ConsultantService consultantService;

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

    @PostMapping("/create")
    public ResponseEntity<ConsultantDto> saveConsultant(@RequestBody @Valid ConsultantDto consultantDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.save(consultantDto));
    }

    @PutMapping("/update")
    public ResponseEntity<ConsultantDto> updateConsultant(@RequestBody @Valid ConsultantDto consultantDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.update(consultantDto));
    }

    @GetMapping("/view-all")
    public ResponseEntity<List<ConsultantDto>> getAllConsultants() {
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.getAllConsultantDetailList());
    }
}
