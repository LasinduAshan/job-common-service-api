package com.job.common.entity;

import com.job.common.dto.AppointmentDetailDto;
import com.job.common.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@Entity
public class AppointmentDetail extends SuperEntity<AppointmentDetailDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long appointmentId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;
    @Column
    private String rejectReason;
    @Column
    private String specialNote;
    @Column
    private String date;
    @Column
    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultant_id")
    private Consultant consultant;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobSeeker_id")
    private JobSeeker jobSeeker;

    @Override
    public AppointmentDetailDto toDto(ModelMapper modelMapper) {
        AppointmentDetailDto appointmentDetailDto = modelMapper.map(this, AppointmentDetailDto.class);
        if (null != this.consultant) {
            appointmentDetailDto.setConsultantName(
                    this.consultant.getFirstName().concat(" ").concat(this.consultant.getLastName()));
        }
        if (null != this.jobSeeker) {
            appointmentDetailDto.setJobSeekerName(this.jobSeeker.getName());
        }
        return appointmentDetailDto;
    }
}
