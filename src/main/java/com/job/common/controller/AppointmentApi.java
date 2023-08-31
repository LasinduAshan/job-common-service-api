package com.job.common.controller;

import com.job.common.dto.AppointmentDetailDto;
import com.job.common.dto.JobSeekerDto;
import com.job.common.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking-service")
@RequiredArgsConstructor
public class AppointmentApi {

    private final AppointmentService appointmentService;
    @PostMapping("/create")
    public ResponseEntity<JobSeekerDto> saveJobSeeker(@RequestBody @Valid JobSeekerDto jobSeekerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.save(jobSeekerDto));
    }

    @PostMapping("/accept-appointment")
    public ResponseEntity<AppointmentDetailDto> acceptAppointment(@RequestBody @Valid AppointmentDetailDto appointmentDetailDto) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.acceptAppointment(appointmentDetailDto));
    }

    @PostMapping("/reject-appointment")
    public ResponseEntity<AppointmentDetailDto> rejectAppointment(@RequestBody @Valid AppointmentDetailDto appointmentDetailDto) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.rejectAppointment(appointmentDetailDto));
    }

    @GetMapping("/view-all-admin")
    public ResponseEntity<List<AppointmentDetailDto>> getAllAppointmentDetailListForAdmin() {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAllAppointmentDetailListForAdmin());
    }

    @GetMapping("/view-all-consultant/{email}/{appointmentStatus}")
    public ResponseEntity<List<AppointmentDetailDto>> getAllAppointmentDetailListForConsultant(@PathVariable String email, @PathVariable String appointmentStatus) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAllAppointmentDetailListForConsultant(email, appointmentStatus));
    }
}
