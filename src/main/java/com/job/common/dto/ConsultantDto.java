package com.job.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.job.common.entity.Consultant;
import com.job.common.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.antlr.v4.runtime.misc.NotNull;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties
public class ConsultantDto extends SuperDto<Consultant> {

    private Long consultantId;
    @NotEmpty
    @NotBlank
    private String firstName;
    @NotEmpty
    @NotBlank
    private String lastName;
    @Email
    private String email;
    @NotEmpty
    @NotBlank
    private String idNo;

    @Pattern(regexp = "^\\d{10}$", message = "invalid mobile number!")
    @NotEmpty
    @NotBlank
    private String contactNo;
    @NotEmpty
    @NotBlank
    private String jobType;
    @NotEmpty
    @NotBlank
    private String country;

    @Min(8)
    private String password;
    private Role role;
    private List<AppointmentDetailDto> appointmentDetailDtoList = new ArrayList<>();
    private List<AvailabilityDto> availabilityDtoList = new ArrayList<>();

    @Override
    public Consultant toEntity(ModelMapper modelMapper) {
        return modelMapper.map(this, Consultant.class);
    }
}
