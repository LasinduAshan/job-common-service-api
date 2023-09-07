package com.job.common.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.job.common.service.SendEmailService;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppointmentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AppointmentServiceImplTest {
    @MockBean
    private AppointmentDetailRepository appointmentDetailRepository;

    @Autowired
    private AppointmentServiceImpl appointmentServiceImpl;

    @MockBean
    private ConsultantRepository consultantRepository;

    @MockBean
    private JobSeekerRepository jobSeekerRepository;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private SendEmailService sendEmailService;

    @Test
    void testSave() {
        when(appointmentDetailRepository.save(Mockito.any())).thenReturn(new AppointmentDetail());

        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setAge(30);
        jobSeeker.setAppointmentDetailList(new ArrayList<>());
        jobSeeker.setContactNo("0763527997");
        jobSeeker.setEmail("jane.doe@example.com");
        jobSeeker.setIdNo("993520976V");
        jobSeeker.setJobSeekerId(1L);
        jobSeeker.setName("Jane");
        jobSeeker.setPreferCountry("Canada");
        jobSeeker.setPreferJobType("Nurse");
        when(modelMapper.map(Mockito.any(), Mockito.<Class<JobSeeker>>any())).thenReturn(jobSeeker);

        JobSeeker jobSeeker2 = new JobSeeker();
        jobSeeker2.setContactNo("0763527997");
        jobSeeker2.setEmail("jane.doe@example.com");
        jobSeeker2.setIdNo("993520976V");
        jobSeeker2.setJobSeekerId(1L);
        jobSeeker2.setName("Jane");
        jobSeeker2.setPreferCountry("Canada");
        jobSeeker2.setPreferJobType("Nurse");
        when(jobSeekerRepository.save(Mockito.any())).thenReturn(jobSeeker2);
        when(consultantRepository.findByCountryAndJobType(Mockito.any(), Mockito.any()))
                .thenReturn(new Consultant());

        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(30);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("0763527997");
        jobSeekerDto.setEmail("jane.doe@example.com");
        jobSeekerDto.setIdNo("993520976V");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Jane");
        jobSeekerDto.setPreferCountry("Canada");
        jobSeekerDto.setPreferJobType("Nurse");
        assertSame(jobSeekerDto, appointmentServiceImpl.save(jobSeekerDto));
        verify(appointmentDetailRepository).save(Mockito.any());
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<JobSeeker>>any());
        verify(jobSeekerRepository).save(Mockito.any());
        verify(consultantRepository).findByCountryAndJobType(Mockito.any(), Mockito.any());
    }

    @Test
    void testSave2() {
        when(appointmentDetailRepository.save(Mockito.any()))
                .thenThrow(new RecordNotFoundException("An error occurred"));

        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setAge(30);
        jobSeeker.setAppointmentDetailList(new ArrayList<>());
        jobSeeker.setContactNo("0763527997");
        jobSeeker.setEmail("jane.doe@example.com");
        jobSeeker.setIdNo("993520976V");
        jobSeeker.setJobSeekerId(1L);
        jobSeeker.setName("Jane");
        jobSeeker.setPreferCountry("Canada");
        jobSeeker.setPreferJobType("Nurse");
        when(modelMapper.map(Mockito.any(), Mockito.<Class<JobSeeker>>any())).thenReturn(jobSeeker);

        JobSeeker jobSeeker2 = new JobSeeker();
        jobSeeker2.setContactNo("0763527997");
        jobSeeker2.setEmail("jane.doe@example.com");
        jobSeeker2.setIdNo("993520976V");
        jobSeeker2.setJobSeekerId(1L);
        jobSeeker2.setName("Jane");
        jobSeeker2.setPreferCountry("Canada");
        jobSeeker2.setPreferJobType("Nurse");
        when(jobSeekerRepository.save(Mockito.any())).thenReturn(jobSeeker2);
        when(consultantRepository.findByCountryAndJobType(Mockito.any(), Mockito.any()))
                .thenReturn(new Consultant());

        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(30);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("0763527997");
        jobSeekerDto.setEmail("jane.doe@example.com");
        jobSeekerDto.setIdNo("993520976V");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Jane");
        jobSeekerDto.setPreferCountry("Canada");
        jobSeekerDto.setPreferJobType("Nurse");
        assertThrows(RecordNotFoundException.class, () -> appointmentServiceImpl.save(jobSeekerDto));
        verify(appointmentDetailRepository).save(Mockito.any());
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<JobSeeker>>any());
        verify(jobSeekerRepository).save(Mockito.any());
        verify(consultantRepository).findByCountryAndJobType(Mockito.any(), Mockito.any());
    }

    @Test
    void testSave3() {
        when(appointmentDetailRepository.save(Mockito.any())).thenReturn(new AppointmentDetail());

        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setAge(1);
        jobSeeker.setAppointmentDetailList(new ArrayList<>());
        jobSeeker.setContactNo("Contact No");
        jobSeeker.setEmail("jane.doe@example.org");
        jobSeeker.setIdNo("Id No");
        jobSeeker.setJobSeekerId(1L);
        jobSeeker.setName("Name");
        jobSeeker.setPreferCountry("GB");
        jobSeeker.setPreferJobType("Prefer Job Type");
        when(modelMapper.map(Mockito.any(), Mockito.<Class<JobSeeker>>any())).thenReturn(jobSeeker);

        JobSeeker jobSeeker2 = new JobSeeker();
        jobSeeker2.setAge(1);
        jobSeeker2.setAppointmentDetailList(new ArrayList<>());
        jobSeeker2.setContactNo("Contact No");
        jobSeeker2.setEmail("jane.doe@example.org");
        jobSeeker2.setIdNo("Id No");
        jobSeeker2.setJobSeekerId(1L);
        jobSeeker2.setName("Name");
        jobSeeker2.setPreferCountry("GB");
        jobSeeker2.setPreferJobType("Prefer Job Type");
        when(jobSeekerRepository.save(Mockito.any())).thenReturn(jobSeeker2);
        when(consultantRepository.findByCountry(Mockito.any())).thenReturn(new Consultant());
        when(consultantRepository.findByCountryAndJobType(Mockito.any(), Mockito.any())).thenReturn(null);

        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");
        assertSame(jobSeekerDto, appointmentServiceImpl.save(jobSeekerDto));
        verify(appointmentDetailRepository).save(Mockito.any());
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<JobSeeker>>any());
        verify(jobSeekerRepository).save(Mockito.any());
        verify(consultantRepository).findByCountry(Mockito.any());
        verify(consultantRepository).findByCountryAndJobType(Mockito.any(), Mockito.any());
    }

    @Test
    void testAcceptAppointment() {
        when(appointmentDetailRepository.save(Mockito.any()))
                .thenThrow(new RecordNotFoundException("An error occurred"));
        when(appointmentDetailRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new AppointmentDetail()));
        assertThrows(RecordNotFoundException.class,
                () -> appointmentServiceImpl.acceptAppointment(new AppointmentDetailDto()));
        verify(appointmentDetailRepository).save(Mockito.any());
        verify(appointmentDetailRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testAcceptAppointment2() {
        when(appointmentDetailRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class,
                () -> appointmentServiceImpl.acceptAppointment(new AppointmentDetailDto()));
        verify(appointmentDetailRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testRejectAppointment() {
        when(appointmentDetailRepository.save(Mockito.any())).thenReturn(new AppointmentDetail());
        when(appointmentDetailRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new AppointmentDetail()));
        AppointmentDetailDto appointmentDetailDto = new AppointmentDetailDto();
        when(modelMapper.map(Mockito.any(), Mockito.<Class<AppointmentDetailDto>>any()))
                .thenReturn(appointmentDetailDto);
        assertSame(appointmentDetailDto, appointmentServiceImpl.rejectAppointment(new AppointmentDetailDto()));
        verify(appointmentDetailRepository).save(Mockito.any());
        verify(appointmentDetailRepository).findById(Mockito.<Long>any());
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<AppointmentDetailDto>>any());
    }

    @Test
    void testRejectAppointment2() {
        when(appointmentDetailRepository.save(Mockito.any())).thenReturn(new AppointmentDetail());
        when(appointmentDetailRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new AppointmentDetail()));
        when(modelMapper.map(Mockito.any(), Mockito.<Class<AppointmentDetailDto>>any()))
                .thenThrow(new RecordNotFoundException("An error occurred"));
        assertThrows(RecordNotFoundException.class,
                () -> appointmentServiceImpl.rejectAppointment(new AppointmentDetailDto()));
        verify(appointmentDetailRepository).save(Mockito.any());
        verify(appointmentDetailRepository).findById(Mockito.<Long>any());
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<AppointmentDetailDto>>any());
    }
    @Test
    void testGetAllAppointmentDetailListForAdmin1() {
        when(appointmentDetailRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(appointmentServiceImpl.getAllAppointmentDetailListForAdmin("ALL").isEmpty());
        verify(appointmentDetailRepository).findAll();
    }

    @Test
    void testGetAllAppointmentDetailListForAdmin2() {
        ArrayList<AppointmentDetail> appointmentDetailList = new ArrayList<>();
        appointmentDetailList.add(new AppointmentDetail());
        when(appointmentDetailRepository.findAll()).thenReturn(appointmentDetailList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AppointmentDetailDto>>any()))
                .thenReturn(new AppointmentDetailDto());
        assertEquals(1, appointmentServiceImpl.getAllAppointmentDetailListForAdmin("ALL").size());
        verify(appointmentDetailRepository).findAll();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<AppointmentDetailDto>>any());
    }

    @Test
    void testGetAllAppointmentDetailListForAdmin3() {
        ArrayList<AppointmentDetail> appointmentDetailList = new ArrayList<>();
        appointmentDetailList.add(new AppointmentDetail());
        appointmentDetailList.add(new AppointmentDetail());
        when(appointmentDetailRepository.findAll()).thenReturn(appointmentDetailList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AppointmentDetailDto>>any()))
                .thenReturn(new AppointmentDetailDto());
        assertEquals(2, appointmentServiceImpl.getAllAppointmentDetailListForAdmin("ALL").size());
        verify(appointmentDetailRepository).findAll();
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<AppointmentDetailDto>>any());
    }
    @Test
    void testGetAllAppointmentDetailListForAdmin4() {
        ArrayList<AppointmentDetail> appointmentDetailList = new ArrayList<>();
        appointmentDetailList.add(new AppointmentDetail());
        appointmentDetailList.add(new AppointmentDetail());
        when(appointmentDetailRepository.findAllByAppointmentStatus(AppointmentStatus.PENDING)).thenReturn(appointmentDetailList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AppointmentDetailDto>>any()))
                .thenReturn(new AppointmentDetailDto());
        assertEquals(2, appointmentServiceImpl.getAllAppointmentDetailListForAdmin("PENDING").size());
        verify(appointmentDetailRepository).findAllByAppointmentStatus(AppointmentStatus.PENDING);
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<AppointmentDetailDto>>any());
    }
    @Test
    void testGetAllAppointmentDetailListForConsultant1() {
        when(consultantRepository.findByEmail(Mockito.any())).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> appointmentServiceImpl
                .getAllAppointmentDetailListForConsultant("jane.doe@example.org", "Appointment Status"));
        verify(consultantRepository).findByEmail(Mockito.any());
    }

    @Test
    void testGetAllAppointmentDetailListForConsultant2() {
        when(consultantRepository.findByEmail(Mockito.any()))
                .thenThrow(new RecordNotFoundException("An error occurred"));
        assertThrows(RecordNotFoundException.class, () -> appointmentServiceImpl
                .getAllAppointmentDetailListForConsultant("jane.doe@example.org", "Appointment Status"));
        verify(consultantRepository).findByEmail(Mockito.any());
    }

    @Test
    void testGetAdminDashboardDetails() {
        when(appointmentDetailRepository.findAllAppointmentCount()).thenReturn(3L);
        when(appointmentDetailRepository.findTodayAppointmentCount()).thenReturn(3L);
        when(consultantRepository.findAllConsultantCount()).thenReturn(3L);
        List<ListItemDto> actualAdminDashboardDetails = appointmentServiceImpl.getAdminDashboardDetails();
        assertEquals(3, actualAdminDashboardDetails.size());
        ListItemDto getResult = actualAdminDashboardDetails.get(1);
        assertEquals("Total Appointments", getResult.getLabel());
        assertNull(getResult.getIsNotAvailable());
        ListItemDto getResult2 = actualAdminDashboardDetails.get(2);
        assertEquals("Total Consultants", getResult2.getLabel());
        assertNull(getResult2.getIsNotAvailable());
        ListItemDto getResult3 = actualAdminDashboardDetails.get(0);
        assertEquals("Today Appointments", getResult3.getLabel());
        assertNull(getResult3.getIsNotAvailable());
        verify(appointmentDetailRepository).findAllAppointmentCount();
        verify(appointmentDetailRepository).findTodayAppointmentCount();
        verify(consultantRepository).findAllConsultantCount();
    }

    @Test
    void testGetAdminDashboardDetails2() {
        when(appointmentDetailRepository.findAllAppointmentCount()).thenReturn(3L);
        when(appointmentDetailRepository.findTodayAppointmentCount()).thenReturn(3L);
        when(consultantRepository.findAllConsultantCount()).thenThrow(new RecordNotFoundException("An error occurred"));
        assertThrows(RecordNotFoundException.class, () -> appointmentServiceImpl.getAdminDashboardDetails());
        verify(appointmentDetailRepository).findAllAppointmentCount();
        verify(appointmentDetailRepository).findTodayAppointmentCount();
        verify(consultantRepository).findAllConsultantCount();
    }

    @Test
    void testGetConsultantDashboardDetails() {
        when(appointmentDetailRepository.findScheduledAppointmentCountForConsultant(Mockito.<Long>any(),
                Mockito.any())).thenReturn(3L);
        when(appointmentDetailRepository.findTodayAppointmentCountForConsultant(Mockito.<Long>any())).thenReturn(3L);
        when(consultantRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new Consultant()));
        List<ListItemDto> actualConsultantDashboardDetails = appointmentServiceImpl
                .getConsultantDashboardDetails("jane.doe@example.com");
        assertEquals(4, actualConsultantDashboardDetails.size());
        ListItemDto getResult = actualConsultantDashboardDetails.get(2);
        assertEquals("Pending Appointments", getResult.getLabel());
        assertNull(getResult.getIsNotAvailable());
        ListItemDto getResult2 = actualConsultantDashboardDetails.get(1);
        assertEquals("Scheduled Appointments", getResult2.getLabel());
        assertNull(getResult2.getIsNotAvailable());
        ListItemDto getResult3 = actualConsultantDashboardDetails.get(3);
        assertEquals("Rejected Appointments", getResult3.getLabel());
        assertNull(getResult3.getIsNotAvailable());
        ListItemDto getResult4 = actualConsultantDashboardDetails.get(0);
        assertEquals("Today Appointments", getResult4.getLabel());
        assertNull(getResult4.getIsNotAvailable());
        verify(appointmentDetailRepository, atLeast(1)).findScheduledAppointmentCountForConsultant(Mockito.<Long>any(),
                Mockito.any());
        verify(appointmentDetailRepository).findTodayAppointmentCountForConsultant(Mockito.<Long>any());
        verify(consultantRepository).findByEmail(Mockito.any());
    }

    @Test
    void testGetConsultantDashboardDetails2() {
        when(appointmentDetailRepository.findTodayAppointmentCountForConsultant(Mockito.<Long>any()))
                .thenThrow(new RecordNotFoundException("An error occurred"));
        when(consultantRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new Consultant()));
        assertThrows(RecordNotFoundException.class,
                () -> appointmentServiceImpl.getConsultantDashboardDetails("jane.doe@example.com"));
        verify(appointmentDetailRepository).findTodayAppointmentCountForConsultant(Mockito.<Long>any());
        verify(consultantRepository).findByEmail(Mockito.any());
    }

    @Test
    void testGetConsultantDashboardDetails3() {
        Consultant consultant = mock(Consultant.class);
        when(consultant.getConsultantId()).thenThrow(new RecordNotFoundException("An error occurred"));
        Optional<Consultant> ofResult = Optional.of(consultant);
        when(consultantRepository.findByEmail(Mockito.any())).thenReturn(ofResult);
        assertThrows(RecordNotFoundException.class,
                () -> appointmentServiceImpl.getConsultantDashboardDetails("jane.doe@example.org"));
        verify(consultantRepository).findByEmail(Mockito.any());
        verify(consultant).getConsultantId();
    }

    @Test
    void testGetConsultantDashboardDetails4() {
        when(consultantRepository.findByEmail(Mockito.any())).thenReturn(Optional.empty());
        new RecordNotFoundException("An error occurred");
        assertThrows(RecordNotFoundException.class,
                () -> appointmentServiceImpl.getConsultantDashboardDetails("jane.doe@example.com"));
        verify(consultantRepository).findByEmail(Mockito.any());
    }

    @Test
    void testCompleteAppointment() {
        when(appointmentDetailRepository.save(Mockito.<AppointmentDetail>any())).thenReturn(new AppointmentDetail());
        when(appointmentDetailRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new AppointmentDetail()));
        AppointmentDetailDto appointmentDetailDto = new AppointmentDetailDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AppointmentDetailDto>>any()))
                .thenReturn(appointmentDetailDto);
        assertSame(appointmentDetailDto, appointmentServiceImpl.completeAppointment(1L));
        verify(appointmentDetailRepository).save(Mockito.<AppointmentDetail>any());
        verify(appointmentDetailRepository).findById(Mockito.<Long>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<AppointmentDetailDto>>any());
    }

    @Test
    void testCompleteAppointment2() {
        when(appointmentDetailRepository.save(Mockito.<AppointmentDetail>any())).thenReturn(new AppointmentDetail());
        when(appointmentDetailRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new AppointmentDetail()));
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AppointmentDetailDto>>any()))
                .thenThrow(new RecordNotFoundException("An error occurred"));
        assertThrows(RecordNotFoundException.class, () -> appointmentServiceImpl.completeAppointment(1L));
        verify(appointmentDetailRepository).save(Mockito.<AppointmentDetail>any());
        verify(appointmentDetailRepository).findById(Mockito.<Long>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<AppointmentDetailDto>>any());
    }

}

