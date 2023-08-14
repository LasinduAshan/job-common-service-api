package com.job.common.repository;

import com.job.common.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {


}
