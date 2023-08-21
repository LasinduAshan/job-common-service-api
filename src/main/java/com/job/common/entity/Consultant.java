package com.job.common.entity;

import com.job.common.dto.ConsultantDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;

@Getter
@Setter
@Entity
public class Consultant extends SuperEntity<ConsultantDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long consultantId;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String idNo;
    @Column
    private String contactNo;
    @Column
    private String address;
    @Column
    private String jobType;
    @Column
    private String country;

    @OneToMany(targetEntity = AppointmentDetail.class, mappedBy = "consultant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AppointmentDetail> appointmentDetailList;

    @OneToMany(targetEntity = Availability.class, mappedBy = "consultant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Availability> availabilityList;

    @Override
    public ConsultantDto toDto(ModelMapper modelMapper) {
        ConsultantDto consultantDto = modelMapper.map(this, ConsultantDto.class);
        if (null != this.availabilityList)
            this.availabilityList.forEach(availability ->
                    consultantDto.getAvailabilityDtoList().add(availability.toDto(modelMapper)));
        return consultantDto;
    }
}
