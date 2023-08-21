package com.job.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.job.common.entity.AppointmentDetail;
import com.job.common.entity.Availability;
import com.job.common.entity.Consultant;
import com.job.common.enums.Days;
import com.job.common.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
//@Builder
@JsonIgnoreProperties
public class AvailabilityDto extends SuperDto<Availability> {

    @Enumerated(EnumType.STRING)
    private Days day;
    private String startTime;
    private String endTime;
    private Integer id;
    private boolean status;


    private Long availabilityId;
    private Boolean isWorkDay;
    private String startHour;
    private String startMinutes;
    private String endHour;
    private String endMinutes;


    @Override
    public Availability toEntity(ModelMapper modelMapper) {
        return modelMapper.map(this, Availability.class);
    }
}
