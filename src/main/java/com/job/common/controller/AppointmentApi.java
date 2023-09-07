package com.job.common.controller;

import com.job.common.dto.AppointmentDetailDto;
import com.job.common.dto.JobSeekerDto;
import com.job.common.dto.ListItemDto;
import com.job.common.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking-service")
@RequiredArgsConstructor
public class AppointmentApi {

    private final AppointmentService appointmentService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobSeekerDto> saveJobSeeker(@RequestBody @Valid JobSeekerDto jobSeekerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.save(jobSeekerDto));
    }

    @PostMapping(value = "/accept-appointment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentDetailDto> acceptAppointment(@RequestBody @Valid AppointmentDetailDto appointmentDetailDto) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.acceptAppointment(appointmentDetailDto));
    }

    @PostMapping(value = "/reject-appointment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentDetailDto> rejectAppointment(@RequestBody @Valid AppointmentDetailDto appointmentDetailDto) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.rejectAppointment(appointmentDetailDto));
    }

    @GetMapping("/view-all-admin/{appointmentStatus}")
    public ResponseEntity<List<AppointmentDetailDto>> getAllAppointmentDetailListForAdmin(@PathVariable String appointmentStatus) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAllAppointmentDetailListForAdmin(appointmentStatus));
    }

    @GetMapping("/view-all-consultant/{email}/{appointmentStatus}")
    public ResponseEntity<List<AppointmentDetailDto>> getAllAppointmentDetailListForConsultant(@PathVariable String email, @PathVariable String appointmentStatus) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAllAppointmentDetailListForConsultant(email, appointmentStatus));
    }

    @GetMapping("/view-admin-dashboard")
    public ResponseEntity<List<ListItemDto>> getAdminDashboardDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAdminDashboardDetails());
    }

    @GetMapping("/view-consultant-dashboard/{email}")
    public ResponseEntity<List<ListItemDto>> getConsultantDashboardDetails(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getConsultantDashboardDetails(email));
    }

    @GetMapping("/complete-appointment/{appointmentId}")
    public ResponseEntity<AppointmentDetailDto> completeAppointment(@PathVariable Long appointmentId) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.completeAppointment(appointmentId));
    }
}
