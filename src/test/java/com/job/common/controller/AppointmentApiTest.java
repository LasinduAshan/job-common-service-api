package com.job.common.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.job.common.dto.AppointmentDetailDto;
import com.job.common.dto.JobSeekerDto;
import com.job.common.dto.ListItemDto;
import com.job.common.entity.AppointmentDetail;
import com.job.common.entity.Consultant;
import com.job.common.entity.JobSeeker;
import com.job.common.repository.AppointmentDetailRepository;
import com.job.common.repository.ConsultantRepository;
import com.job.common.repository.JobSeekerRepository;
import com.job.common.service.AppointmentService;
import com.job.common.service.SendEmailService;
import com.job.common.service.impl.AppointmentServiceImpl;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

class AppointmentApiTest {
    @Test
    void testSaveJobSeeker() {

        AppointmentDetailRepository appointmentDetailRepository = mock(AppointmentDetailRepository.class);
        when(appointmentDetailRepository.save(Mockito.any())).thenReturn(new AppointmentDetail());

        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setAge(1);
        jobSeeker.setAppointmentDetailList(new ArrayList<>());
        jobSeeker.setContactNo("0762025265");
        jobSeeker.setEmail("jane.doe@example.com");
        jobSeeker.setIdNo("993520975V");
        jobSeeker.setJobSeekerId(1L);
        jobSeeker.setName("Jane");
        jobSeeker.setPreferCountry("Canada");
        jobSeeker.setPreferJobType("Nurse");
        JobSeekerRepository jobSeekerRepository = mock(JobSeekerRepository.class);
        when(jobSeekerRepository.save(Mockito.any())).thenReturn(jobSeeker);
        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        when(consultantRepository.findByCountryAndJobType(Mockito.any(), Mockito.any()))
                .thenReturn(new Consultant());
        AppointmentApi appointmentApi = new AppointmentApi(new AppointmentServiceImpl(appointmentDetailRepository,
                new ModelMapper(), jobSeekerRepository, consultantRepository, mock(SendEmailService.class)));

        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("0762025265");
        jobSeekerDto.setEmail("jane.doe@example.com");
        jobSeekerDto.setIdNo("993520975V");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("Canada");
        jobSeekerDto.setPreferJobType("Nurse");
        ResponseEntity<JobSeekerDto> actualSaveJobSeekerResult = appointmentApi.saveJobSeeker(jobSeekerDto);
        assertTrue(actualSaveJobSeekerResult.hasBody());
        assertTrue(actualSaveJobSeekerResult.getHeaders().isEmpty());
        assertEquals(200, actualSaveJobSeekerResult.getStatusCodeValue());
        verify(appointmentDetailRepository).save(Mockito.any());
        verify(jobSeekerRepository).save(Mockito.any());
        verify(consultantRepository).findByCountryAndJobType(Mockito.any(), Mockito.any());
    }

    @Test
    void testAcceptAppointment() {

        AppointmentService appointmentService = mock(AppointmentService.class);
        when(appointmentService.acceptAppointment(Mockito.any()))
                .thenReturn(new AppointmentDetailDto());
        AppointmentApi appointmentApi = new AppointmentApi(appointmentService);
        AppointmentDetailDto appointmentDetailDto = new AppointmentDetailDto();
        ResponseEntity<AppointmentDetailDto> actualAcceptAppointmentResult = appointmentApi
                .acceptAppointment(appointmentDetailDto);
        assertEquals(appointmentDetailDto, actualAcceptAppointmentResult.getBody());
        assertTrue(actualAcceptAppointmentResult.getHeaders().isEmpty());
        assertEquals(200, actualAcceptAppointmentResult.getStatusCodeValue());
        verify(appointmentService).acceptAppointment(Mockito.any());
    }

    @Test
    void testRejectAppointment() {

        AppointmentDetail appointmentDetail = mock(AppointmentDetail.class);
        when(appointmentDetail.toDto(Mockito.any())).thenReturn(new AppointmentDetailDto());
        AppointmentDetailRepository appointmentDetailRepository = mock(AppointmentDetailRepository.class);
        when(appointmentDetailRepository.save(Mockito.any())).thenReturn(appointmentDetail);
        when(appointmentDetailRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new AppointmentDetail()));
        AppointmentApi appointmentApi = new AppointmentApi(
                new AppointmentServiceImpl(appointmentDetailRepository, new ModelMapper(), mock(JobSeekerRepository.class),
                        mock(ConsultantRepository.class), mock(SendEmailService.class)));
        AppointmentDetailDto appointmentDetailDto = new AppointmentDetailDto();
        ResponseEntity<AppointmentDetailDto> actualRejectAppointmentResult = appointmentApi
                .rejectAppointment(appointmentDetailDto);
        assertEquals(appointmentDetailDto, actualRejectAppointmentResult.getBody());
        assertTrue(actualRejectAppointmentResult.getHeaders().isEmpty());
        assertEquals(200, actualRejectAppointmentResult.getStatusCodeValue());
        verify(appointmentDetailRepository).save(Mockito.any());
        verify(appointmentDetailRepository).findById(Mockito.<Long>any());
        verify(appointmentDetail).toDto(Mockito.any());
    }

    @Test
    void testGetAllAppointmentDetailListForAdmin() {

        AppointmentDetailRepository appointmentDetailRepository = mock(AppointmentDetailRepository.class);
        when(appointmentDetailRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<AppointmentDetailDto>> actualAllAppointmentDetailListForAdmin = (new AppointmentApi(
                new AppointmentServiceImpl(appointmentDetailRepository, new ModelMapper(), mock(JobSeekerRepository.class),
                        mock(ConsultantRepository.class), mock(SendEmailService.class)))).getAllAppointmentDetailListForAdmin();
        assertTrue(actualAllAppointmentDetailListForAdmin.hasBody());
        assertEquals(200, actualAllAppointmentDetailListForAdmin.getStatusCodeValue());
        assertTrue(actualAllAppointmentDetailListForAdmin.getHeaders().isEmpty());
        verify(appointmentDetailRepository).findAll();
    }

    @Test
    void testGetAllAppointmentDetailListForConsultant() {

        AppointmentService appointmentService = mock(AppointmentService.class);
        when(appointmentService.getAllAppointmentDetailListForConsultant(Mockito.any(), Mockito.any()))
                .thenReturn(new ArrayList<>());
        ResponseEntity<List<AppointmentDetailDto>> actualAllAppointmentDetailListForConsultant = (new AppointmentApi(
                appointmentService)).getAllAppointmentDetailListForConsultant("jane.doe@example.com", "PENDING");
        assertTrue(actualAllAppointmentDetailListForConsultant.hasBody());
        assertEquals(200, actualAllAppointmentDetailListForConsultant.getStatusCodeValue());
        assertTrue(actualAllAppointmentDetailListForConsultant.getHeaders().isEmpty());
        verify(appointmentService).getAllAppointmentDetailListForConsultant(Mockito.any(), Mockito.any());
    }

    @Test
    void testGetAdminDashboardDetails() {

        AppointmentDetailRepository appointmentDetailRepository = mock(AppointmentDetailRepository.class);
        when(appointmentDetailRepository.findAllAppointmentCount()).thenReturn(3L);
        when(appointmentDetailRepository.findTodayAppointmentCount()).thenReturn(3L);
        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        when(consultantRepository.findAllConsultantCount()).thenReturn(3L);
        ResponseEntity<List<ListItemDto>> actualAdminDashboardDetails = (new AppointmentApi(
                new AppointmentServiceImpl(appointmentDetailRepository, new ModelMapper(), mock(JobSeekerRepository.class),
                        consultantRepository, mock(SendEmailService.class)))).getAdminDashboardDetails();
        List<ListItemDto> body = actualAdminDashboardDetails.getBody();
        assertEquals(3, body.size());
        assertTrue(actualAdminDashboardDetails.hasBody());
        assertTrue(actualAdminDashboardDetails.getHeaders().isEmpty());
        assertEquals(200, actualAdminDashboardDetails.getStatusCodeValue());
        ListItemDto getResult = body.get(2);
        assertEquals("Total Consultants", getResult.getLabel());
        assertNull(getResult.getIsNotAvailable());
        ListItemDto getResult2 = body.get(0);
        assertEquals("Today Appointments", getResult2.getLabel());
        assertNull(getResult2.getIsNotAvailable());
        ListItemDto getResult3 = body.get(1);
        assertEquals("Total Appointments", getResult3.getLabel());
        assertNull(getResult3.getIsNotAvailable());
        verify(appointmentDetailRepository).findAllAppointmentCount();
        verify(appointmentDetailRepository).findTodayAppointmentCount();
        verify(consultantRepository).findAllConsultantCount();
    }

    @Test
    void testGetConsultantDashboardDetails() {

        AppointmentDetailRepository appointmentDetailRepository = mock(AppointmentDetailRepository.class);
        when(appointmentDetailRepository.findScheduledAppointmentCountForConsultant(Mockito.<Long>any(),
                Mockito.any())).thenReturn(3L);
        when(appointmentDetailRepository.findTodayAppointmentCountForConsultant(Mockito.<Long>any())).thenReturn(3L);
        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        when(consultantRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new Consultant()));
        ResponseEntity<List<ListItemDto>> actualConsultantDashboardDetails = (new AppointmentApi(
                new AppointmentServiceImpl(appointmentDetailRepository, new ModelMapper(), mock(JobSeekerRepository.class),
                        consultantRepository, mock(SendEmailService.class))))
                .getConsultantDashboardDetails("jane.doe@example.org");
        List<ListItemDto> body = actualConsultantDashboardDetails.getBody();
        assertEquals(4, body.size());
        assertTrue(actualConsultantDashboardDetails.hasBody());
        assertTrue(actualConsultantDashboardDetails.getHeaders().isEmpty());
        assertEquals(200, actualConsultantDashboardDetails.getStatusCodeValue());
        ListItemDto getResult = body.get(1);
        assertEquals("Scheduled Appointments", getResult.getLabel());
        assertNull(getResult.getIsNotAvailable());
        ListItemDto getResult2 = body.get(0);
        assertEquals("Today Appointments", getResult2.getLabel());
        assertNull(getResult2.getIsNotAvailable());
        ListItemDto getResult3 = body.get(3);
        assertEquals("Rejected Appointments", getResult3.getLabel());
        assertNull(getResult3.getIsNotAvailable());
        ListItemDto getResult4 = body.get(2);
        assertEquals("Pending Appointments", getResult4.getLabel());
        assertNull(getResult4.getIsNotAvailable());
        verify(appointmentDetailRepository, atLeast(1)).findScheduledAppointmentCountForConsultant(Mockito.<Long>any(),
                Mockito.any());
        verify(appointmentDetailRepository).findTodayAppointmentCountForConsultant(Mockito.<Long>any());
        verify(consultantRepository).findByEmail(Mockito.any());
    }
}

