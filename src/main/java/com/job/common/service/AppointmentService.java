package com.job.common.service;


import com.job.common.dto.AppointmentDetailDto;
import com.job.common.dto.JobSeekerDto;
import com.job.common.dto.ListItemDto;

import java.util.List;

public interface AppointmentService {

    JobSeekerDto save(JobSeekerDto jobSeekerDto);

    AppointmentDetailDto acceptAppointment(AppointmentDetailDto appointmentDetailDto);

    AppointmentDetailDto rejectAppointment(AppointmentDetailDto appointmentDetailDto);

    List<AppointmentDetailDto> getAllAppointmentDetailListForAdmin(String appointmentStatus);

    List<AppointmentDetailDto> getAllAppointmentDetailListForConsultant(String email, String appointmentStatus);

    List<ListItemDto> getAdminDashboardDetails();

    List<ListItemDto> getConsultantDashboardDetails(String email);

    AppointmentDetailDto completeAppointment(Long appointmentId);

}
