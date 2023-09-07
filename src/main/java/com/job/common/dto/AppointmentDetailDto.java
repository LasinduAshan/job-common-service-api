package com.job.common.dto;

import com.job.common.entity.AppointmentDetail;
import com.job.common.enums.AppointmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.modelmapper.ModelMapper;


@Data
public class AppointmentDetailDto extends SuperDto<AppointmentDetail> {

    private Long appointmentId;
    private String rejectReason;
    private String specialNote;
    @NotEmpty
    @NotBlank
    private String date;
    @NotEmpty
    @NotBlank
    private String time;
    private AppointmentStatus appointmentStatus;


    private String consultantName;
    private String consultantEmail;
    private String jobSeekerName;
    private String jobSeekerEmail;
    private String jobSeekerJobType;
    private String jobSeekerCountry;
    private Long consultantId;

    @Override
    public AppointmentDetail toEntity(ModelMapper modelMapper) {
        return modelMapper.map(this, AppointmentDetail.class);
    }
}
