package com.job.common.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.job.common.dto.ConsultantDto;
import com.job.common.dto.ListItemDto;
import com.job.common.service.ConsultantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consultant-service")
@RequiredArgsConstructor
public class ConsultantApi {

    private final ConsultantService consultantService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsultantDto> saveConsultant(@RequestBody @Valid ConsultantDto consultantDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.save(consultantDto));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsultantDto> updateConsultant(@RequestBody @Valid ConsultantDto consultantDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.update(consultantDto));
    }

    @GetMapping("/view/{consultantId}")
    public ResponseEntity<ConsultantDto> getConsultantDetailById(@PathVariable Long consultantId) {
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.getConsultantDetailById(consultantId));
    }

    @GetMapping("/view/profile/{email}")
    public ResponseEntity<ConsultantDto> getConsultantDetailByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.getConsultantDetailByEmail(email));
    }

    @GetMapping("/view-all")
    public ResponseEntity<List<ConsultantDto>> getAllConsultants() {
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.getAllConsultantDetailList());
    }

    @GetMapping("/availability-time-slot/{date}/{day}/{consultantId}")
    public ResponseEntity<List<ListItemDto>> getAvailabilityTimeSlots(@PathVariable String date, @PathVariable String day, @PathVariable Long consultantId) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.getAvailabilityTimeSlots(date, day, consultantId));
    }

    @DeleteMapping("/{consultantId}")
    public ResponseEntity<ConsultantDto> deleteConsultantDetailById(@PathVariable Long consultantId) {
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.delete(consultantId));
    }

}
