package com.job.common.repository;

import com.job.common.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {


}
