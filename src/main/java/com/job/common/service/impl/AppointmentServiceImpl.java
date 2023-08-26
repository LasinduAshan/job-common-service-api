package com.job.common.service.impl;

import com.job.common.dto.AppointmentDetailDto;
import com.job.common.dto.JobSeekerDto;
import com.job.common.entity.AppointmentDetail;
import com.job.common.entity.Consultant;
import com.job.common.entity.JobSeeker;
import com.job.common.enums.AppointmentStatus;
import com.job.common.exception.RecordNotFoundException;
import com.job.common.repository.AppointmentDetailRepository;
import com.job.common.repository.ConsultantRepository;
import com.job.common.repository.JobSeekerRepository;
import com.job.common.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentDetailRepository appointmentDetailRepository;
    private final ModelMapper modelMapper;
    private final JobSeekerRepository jobSeekerRepository;
    private final ConsultantRepository consultantRepository;


    @Transactional
    @Override
    public JobSeekerDto save(JobSeekerDto jobSeekerDto) {

        JobSeeker jobSeeker = modelMapper.map(jobSeekerDto, JobSeeker.class);
        JobSeeker saveJobSeeker = jobSeekerRepository.save(jobSeeker);
        Consultant consultant = consultantRepository.findByCountryAndJobType(jobSeeker.getPreferCountry(), jobSeeker.getPreferJobType());
        if (consultant == null) {
            consultant = consultantRepository.findByCountry(consultant.getCountry());
        }

        AppointmentDetail appointment = new AppointmentDetail();
        appointment.setJobSeeker(saveJobSeeker);
        appointment.setConsultant(consultant);
        appointment.setAppointmentStatus(AppointmentStatus.PENDING);

        appointmentDetailRepository.save(appointment);

        return jobSeekerDto;
    }

    @Override
    public AppointmentDetailDto acceptAppointment(AppointmentDetailDto appointmentDetailDto) {

        Optional<AppointmentDetail> detailOptional = appointmentDetailRepository.findById(appointmentDetailDto.getAppointmentId());

        if (detailOptional.isPresent()) {
            AppointmentDetail appointmentDetail = detailOptional.get();

            appointmentDetail.setDate(appointmentDetailDto.getDate());
            appointmentDetail.setAppointmentStatus(AppointmentStatus.SCHEDULED);
            appointmentDetail.setTime(appointmentDetailDto.getTime());
            appointmentDetail.setSpecialNote(appointmentDetailDto.getSpecialNote());

            appointmentDetailRepository.save(appointmentDetail);
        } else {
            throw new RecordNotFoundException("Appointment record not found");
        }

        return appointmentDetailDto;
    }

    @Override
    public List<AppointmentDetailDto> getAllAppointmentDetailListForAdmin() {

        return appointmentDetailRepository.findAll()
                .stream()
                .map(appointmentDetail -> appointmentDetail.toDto(modelMapper))
                .toList();
    }
}
