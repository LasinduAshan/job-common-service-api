package com.job.common.repository;

import com.job.common.entity.AppointmentDetail;
import com.job.common.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppointmentDetailRepository extends JpaRepository<AppointmentDetail, Long> {

    List<AppointmentDetail> findAllByAppointmentStatusAndConsultantConsultantId(AppointmentStatus appointmentStatus, Long consultantID);

    List<AppointmentDetail> findAllByDateAndConsultant_ConsultantIdAndAppointmentStatus(String date, Long consultantId, AppointmentStatus appointmentStatus);

}
