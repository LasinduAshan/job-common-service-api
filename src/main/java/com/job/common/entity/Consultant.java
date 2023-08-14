package com.job.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
//public class Consultant extends SuperEntity<QuotationSummaryDto> {
public class Consultant {
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
    private Set<AppointmentDetail> appointmentDetailList;

}
