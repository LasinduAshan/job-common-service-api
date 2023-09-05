package com.job.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.job.common.entity.Availability;
import com.job.common.enums.Days;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;


@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties
public class AvailabilityDto extends SuperDto<Availability> {

    private Long availabilityId;
    @Enumerated(EnumType.STRING)
    private Days day;
    private Boolean isWorkDay;
    @NotEmpty
    @NotBlank
    private String startHour;
    @NotEmpty
    @NotBlank
    private String startMinutes;
    @NotEmpty
    @NotBlank
    private String endHour;
    @NotEmpty
    @NotBlank
    private String endMinutes;
    private String timeSlots;


    @Override
    public Availability toEntity(ModelMapper modelMapper) {
        return modelMapper.map(this, Availability.class);
    }
}
