package com.job.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class JobSeeker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long jobSeekerId;

    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String idNo;
    @Column
    private String contactNo;
    @Column
    private String preferJobType;
    @Column
    private String preferCountry;
    @Column
    private Integer age;

    @OneToMany(targetEntity = AppointmentDetail.class, mappedBy = "jobSeeker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AppointmentDetail> appointmentDetailList = new ArrayList<>();

}
