package com.job.common.dto;

import com.job.common.entity.AppointmentDetail;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class JobSeekerDto {

    private Long jobSeekerId;
    private String name;
    private String email;
    private String idNo;
    private String contactNo;
    private String preferJobType;
    private String preferCountry;
    private List<AppointmentDetail> appointmentDetailList = new ArrayList<>();

}
