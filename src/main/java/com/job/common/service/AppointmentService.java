package com.job.common.service;


import com.job.common.dto.AppointmentDetailDto;
import com.job.common.dto.JobSeekerDto;

import java.util.List;

public interface AppointmentService {

    JobSeekerDto save(JobSeekerDto jobSeekerDto);

    AppointmentDetailDto acceptAppointment(AppointmentDetailDto appointmentDetailDto);

    List<AppointmentDetailDto> getAllAppointmentDetailListForAdmin();

}
