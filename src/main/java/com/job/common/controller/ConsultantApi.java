package com.job.common.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.job.common.dto.AvailabilityDto;
import com.job.common.dto.ConsultantDto;
import com.job.common.dto.ListItemDto;
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

    @PostMapping("/create")
    public ResponseEntity<ConsultantDto> saveConsultant(@RequestBody @Valid ConsultantDto consultantDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.save(consultantDto));
    }

    @PutMapping("/update")
    public ResponseEntity<ConsultantDto> updateConsultant(@RequestBody @Valid ConsultantDto consultantDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.update(consultantDto));
    }

    @GetMapping("/view/{consultantId}")
    public ResponseEntity<ConsultantDto> getConsultantDetailById(@PathVariable Long consultantId) {
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.getConsultantDetailById(consultantId));
    }
    @GetMapping("/view-all")
    public ResponseEntity<List<ConsultantDto>> getAllConsultants() {
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.getAllConsultantDetailList());
    }

    @GetMapping("/availability-time-slot/{date}/{day}/{consultantId}")
    public ResponseEntity<List<ListItemDto>> getAvailabilityTimeSlots(@PathVariable String date, @PathVariable String day, @PathVariable Long consultantId) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.getAvailabilityTimeSlots(date, day, consultantId));
    }

}
