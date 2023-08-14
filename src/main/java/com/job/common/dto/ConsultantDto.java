package com.job.common.dto;

import com.job.common.entity.AppointmentDetail;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
public class ConsultantDto {

    private Long consultantId;
    private String firstName;
    private String lastName;
    private String email;
    private String idNo;
    private String contactNo;
    private String address;
    private String jobType;
    private String country;
    private Set<AppointmentDetail> appointmentDetailList;

}
