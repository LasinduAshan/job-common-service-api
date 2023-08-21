package com.job.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.job.common.entity.AppointmentDetail;
import com.job.common.entity.Consultant;
import com.job.common.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Set;

@Data
//@Builder
@JsonIgnoreProperties
public class ConsultantDto extends SuperDto<Consultant> {

    private Long consultantId;
    @NotNull
    @NotEmpty
    @NotBlank
    private String firstName;
    @NotNull
    @NotEmpty
    @NotBlank
    private String lastName;
    @Email
    private String email;
    @NotNull
    @NotEmpty
    @NotBlank
    private String idNo;

    @Pattern(regexp = "^\\d{10}$", message = "invalid mobile number!")
    @NotNull
    @NotEmpty
    @NotBlank
    private String contactNo;
    private String address;
    @NotNull
    @NotEmpty
    @NotBlank
    private String jobType;
    @NotNull
    @NotEmpty
    @NotBlank
    private String country;

    @Min(8)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private List<AppointmentDetail> appointmentDetailList;
    private List<AvailabilityDto> availabilityDtoList;


    @Override
    public Consultant toEntity(ModelMapper modelMapper) {
        return modelMapper.map(this, Consultant.class);
    }
}
