package com.job.common.entity;

import com.job.common.dto.AvailabilityDto;
import com.job.common.enums.Days;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@Entity
public class Availability extends SuperEntity<AvailabilityDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long availabilityId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Days day;
    @Column
    private Boolean isWorkDay;
    @Column
    private String startHour;
    @Column
    private String startMinutes;
    @Column
    private String endHour;
    @Column
    private String endMinutes;
    @Column
    private String timeSlots;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultant_id")
    private Consultant consultant;

    @Override
    public AvailabilityDto toDto(ModelMapper modelMapper) {
        return modelMapper.map(this, AvailabilityDto.class);
    }
}
