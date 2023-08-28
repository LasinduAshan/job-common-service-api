package com.job.common.repository;

import com.job.common.entity.Availability;
import com.job.common.enums.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    Optional<Availability> findByDayAndConsultantConsultantId(Days day, Long consultantId);

}
