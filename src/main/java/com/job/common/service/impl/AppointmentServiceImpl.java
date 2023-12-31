package com.job.common.service.impl;

import com.job.common.dto.AppointmentDetailDto;
import com.job.common.dto.JobSeekerDto;
import com.job.common.dto.ListItemDto;
import com.job.common.entity.AppointmentDetail;
import com.job.common.entity.Consultant;
import com.job.common.entity.JobSeeker;
import com.job.common.enums.AppointmentStatus;
import com.job.common.exception.RecordNotFoundException;
import com.job.common.repository.AppointmentDetailRepository;
import com.job.common.repository.ConsultantRepository;
import com.job.common.repository.JobSeekerRepository;
import com.job.common.service.AppointmentService;
import com.job.common.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentDetailRepository appointmentDetailRepository;
    private final ModelMapper modelMapper;
    private final JobSeekerRepository jobSeekerRepository;
    private final ConsultantRepository consultantRepository;
    private final SendEmailService sendEmailService;


    @Transactional
    @Override
    public JobSeekerDto save(JobSeekerDto jobSeekerDto) {

        JobSeeker jobSeeker = modelMapper.map(jobSeekerDto, JobSeeker.class);
        JobSeeker saveJobSeeker = jobSeekerRepository.save(jobSeeker);
        Consultant consultant = consultantRepository.findByCountryAndJobType(jobSeeker.getPreferCountry(), jobSeeker.getPreferJobType());
        if (consultant == null) {
            consultant = consultantRepository.findByCountry(jobSeeker.getPreferCountry());
        }

        if (consultant == null) {
            throw new RecordNotFoundException("Consultant not registered in this system.");
        }

        AppointmentDetail appointment = new AppointmentDetail();
        appointment.setJobSeeker(saveJobSeeker);
        appointment.setConsultant(consultant);
        appointment.setAppointmentStatus(AppointmentStatus.PENDING);

        appointmentDetailRepository.save(appointment);

        return jobSeekerDto;
    }

    @Transactional
    @Override
    public AppointmentDetailDto acceptAppointment(AppointmentDetailDto appointmentDetailDto) {

        Optional<AppointmentDetail> detailOptional = appointmentDetailRepository.findById(appointmentDetailDto.getAppointmentId());

        if (detailOptional.isPresent()) {
            AppointmentDetail appointmentDetail = detailOptional.get();

            appointmentDetail.setDate(appointmentDetailDto.getDate());
            appointmentDetail.setAppointmentStatus(AppointmentStatus.SCHEDULED);
            appointmentDetail.setTime(appointmentDetailDto.getTime());
            appointmentDetail.setSpecialNote(appointmentDetailDto.getSpecialNote());

            AppointmentDetail save = appointmentDetailRepository.save(appointmentDetail);

            // Define a DateTimeFormatter for parsing and formatting
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("h:mm a");

            // Parse the input time
            LocalTime parsedTime = LocalTime.parse(appointmentDetailDto.getTime(), inputFormatter);

            // Format the parsed time in 12-hour format with AM/PM
            String formattedTime = parsedTime.format(outputFormatter);


            String jobSeekerEmail = appointmentDetail.getJobSeeker().getEmail();
            String message = "Your appointment date and time:".concat(appointmentDetailDto.getDate()
                    .concat(" : ").concat(formattedTime));

            if (null != appointmentDetailDto.getSpecialNote() && !appointmentDetailDto.getSpecialNote().isEmpty()) {
                message = message.concat("\n").concat(appointmentDetailDto.getSpecialNote());
            }
            sendEmailService.sendEmail(jobSeekerEmail, "Your Appointment Details", message);
            appointmentDetailDto = save.toDto(modelMapper);

        } else {
            throw new RecordNotFoundException("Appointment record not found");
        }

        return appointmentDetailDto;
    }

    @Override
    public AppointmentDetailDto rejectAppointment(AppointmentDetailDto appointmentDetailDto) {

        Optional<AppointmentDetail> detailOptional = appointmentDetailRepository.findById(appointmentDetailDto.getAppointmentId());
        if (detailOptional.isPresent()) {
            AppointmentDetail appointmentDetail = detailOptional.get();
            appointmentDetail.setRejectReason(appointmentDetailDto.getRejectReason());
            appointmentDetail.setAppointmentStatus(AppointmentStatus.REJECTED);
            AppointmentDetail save = appointmentDetailRepository.save(appointmentDetail);
            appointmentDetailDto = save.toDto(modelMapper);
        } else {
            throw new RecordNotFoundException("Appointment record not found");
        }
        return appointmentDetailDto;
    }

    @Override
    public List<AppointmentDetailDto> getAllAppointmentDetailListForAdmin(String appointmentStatus) {

        if (appointmentStatus.equals("ALL")) {
            return appointmentDetailRepository.findAll()
                    .stream()
                    .map(appointmentDetail -> appointmentDetail.toDto(modelMapper))
                    .toList();
        } else {
            return appointmentDetailRepository.findAllByAppointmentStatus(AppointmentStatus.valueOf(appointmentStatus))
                    .stream()
                    .map(appointmentDetail -> appointmentDetail.toDto(modelMapper))
                    .toList();
        }

    }

    @Override
    public List<AppointmentDetailDto> getAllAppointmentDetailListForConsultant(String email, String appointmentStatus) {
        Optional<Consultant> consultantOptional = consultantRepository.findByEmail(email);

        if (consultantOptional.isPresent()) {
            return appointmentDetailRepository
                    .findAllByAppointmentStatusAndConsultantConsultantId(
                            AppointmentStatus.valueOf(appointmentStatus), consultantOptional.get().getConsultantId())
                    .stream()
                    .map(appointmentDetail -> appointmentDetail.toDto(modelMapper))
                    .toList();
        } else {
            throw new RecordNotFoundException("Result not found ");
        }
    }

    @Override
    public List<ListItemDto> getAdminDashboardDetails() {

        List<ListItemDto> listItemDtoList = new ArrayList<>();

        listItemDtoList.add(
                ListItemDto.builder()
                        .label("Today Appointments")
                        .value(appointmentDetailRepository.findTodayAppointmentCount())
                        .build());

        listItemDtoList.add(
                ListItemDto.builder()
                        .label("Total Appointments")
                        .value(appointmentDetailRepository.findAllAppointmentCount())
                        .build());

        listItemDtoList.add(
                ListItemDto.builder()
                        .label("Total Consultants")
                        .value(consultantRepository.findAllConsultantCount())
                        .build());

        return listItemDtoList;
    }

    @Override
    public List<ListItemDto> getConsultantDashboardDetails(String email) {

        Optional<Consultant> consultantOptional = consultantRepository.findByEmail(email);
        List<ListItemDto> listItemDtoList = new ArrayList<>();

        if (consultantOptional.isPresent()) {

            Long consultantId = consultantOptional.get().getConsultantId();
            listItemDtoList.add(
                    ListItemDto.builder()
                            .label("Today Appointments")
                            .value(appointmentDetailRepository.findTodayAppointmentCountForConsultant(consultantId))
                            .build());

            listItemDtoList.add(
                    ListItemDto.builder()
                            .label("Scheduled Appointments")
                            .value(appointmentDetailRepository.findScheduledAppointmentCountForConsultant(
                                    consultantId, String.valueOf(AppointmentStatus.SCHEDULED)))
                            .build());

            listItemDtoList.add(
                    ListItemDto.builder()
                            .label("Pending Appointments")
                            .value(appointmentDetailRepository.findScheduledAppointmentCountForConsultant(
                                    consultantId, String.valueOf(AppointmentStatus.PENDING)))
                            .build());

            listItemDtoList.add(
                    ListItemDto.builder()
                            .label("Rejected Appointments")
                            .value(appointmentDetailRepository.findScheduledAppointmentCountForConsultant(
                                    consultantId, String.valueOf(AppointmentStatus.REJECTED)))
                            .build());

        } else {
            throw new RecordNotFoundException("Consultant record not found");
        }

        return listItemDtoList;
    }

    @Override
    public AppointmentDetailDto completeAppointment(Long appointmentId) {
        Optional<AppointmentDetail> appointmentDetailOptional = appointmentDetailRepository.findById(appointmentId);
        if (appointmentDetailOptional.isPresent()) {
            AppointmentDetail appointmentDetail = appointmentDetailOptional.get();
            appointmentDetail.setAppointmentStatus(AppointmentStatus.COMPLETED);
            AppointmentDetail save = appointmentDetailRepository.save(appointmentDetail);
            return save.toDto(modelMapper);
        } else {
            throw new RecordNotFoundException("Appointment record not found");
        }
    }
}
