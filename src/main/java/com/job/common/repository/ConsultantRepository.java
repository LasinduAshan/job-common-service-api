package com.job.common.repository;

import com.job.common.entity.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long> {

    Consultant findByCountryAndJobType(String country, String jobType);
//    Consultant findTopByCountryAndJobType(String country, String jobType);

    Consultant findByCountry(String country);
    Optional<Consultant> findByEmail(String email);

    @Query(value = "SELECT COUNT(*) FROM consultant", nativeQuery = true)
    Long findAllConsultantCount();

}
