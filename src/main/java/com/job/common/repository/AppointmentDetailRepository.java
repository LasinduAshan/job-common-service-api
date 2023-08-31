package com.job.common.repository;

import com.job.common.entity.AppointmentDetail;
import com.job.common.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppointmentDetailRepository extends JpaRepository<AppointmentDetail, Long> {

    List<AppointmentDetail> findAllByAppointmentStatusAndConsultantConsultantId(AppointmentStatus appointmentStatus, Long consultantID);

    List<AppointmentDetail> findAllByDateAndConsultant_ConsultantIdAndAppointmentStatus(String date, Long consultantId, AppointmentStatus appointmentStatus);

    @Query(value = "SELECT COUNT(*) FROM appointment_detail WHERE DATE(created_date) = CURDATE()", nativeQuery = true)
    Long findTodayAppointmentCount();

    @Query(value = "SELECT COUNT(*) FROM appointment_detail", nativeQuery = true)
    Long findAllAppointmentCount();


    @Query(value = "SELECT COUNT(*) FROM appointment_detail WHERE DATE(created_date) = CURDATE() AND consultant_id =?1", nativeQuery = true)
    Long findTodayAppointmentCountForConsultant(Long consultantId);

    @Query(value = "SELECT COUNT(*) FROM appointment_detail WHERE consultant_id =?1 AND appointment_status =?2", nativeQuery = true)
    Long findScheduledAppointmentCountForConsultant(Long consultantId, String appointmentStatus);


}
