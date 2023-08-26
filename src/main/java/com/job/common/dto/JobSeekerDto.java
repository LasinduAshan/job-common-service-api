package com.job.common.dto;

import com.job.common.entity.AppointmentDetail;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class JobSeekerDto {

    private Long jobSeekerId;
    @NotEmpty
    @NotBlank
    private String name;
    @Email
    private String email;
    private String idNo;
    @Pattern(regexp = "^\\d{10}$", message = "invalid mobile number!")
    @NotNull
    @NotEmpty
    @NotBlank
    private String contactNo;
    @NotNull
    @NotEmpty
    @NotBlank
    private String preferJobType;
    @NotNull
    @NotEmpty
    @NotBlank
    private String preferCountry;

    private Integer age;
    private List<AppointmentDetail> appointmentDetailList = new ArrayList<>();

}
