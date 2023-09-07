package com.job.common.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.job.common.entity.AppointmentDetail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class JobSeekerDtoTest {
    @Test
    void testCanEqual() {
        assertFalse((new JobSeekerDto()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
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

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertTrue(jobSeekerDto.canEqual(jobSeekerDto2));
    }

    @Test
    void testConstructor() {
        JobSeekerDto actualJobSeekerDto = new JobSeekerDto();
        actualJobSeekerDto.setAge(1);
        ArrayList<AppointmentDetail> appointmentDetailList = new ArrayList<>();
        actualJobSeekerDto.setAppointmentDetailList(appointmentDetailList);
        actualJobSeekerDto.setContactNo("Contact No");
        actualJobSeekerDto.setEmail("jane.doe@example.org");
        actualJobSeekerDto.setIdNo("Id No");
        actualJobSeekerDto.setJobSeekerId(1L);
        actualJobSeekerDto.setName("Name");
        actualJobSeekerDto.setPreferCountry("GB");
        actualJobSeekerDto.setPreferJobType("Prefer Job Type");
        String actualToStringResult = actualJobSeekerDto.toString();
        assertEquals(1, actualJobSeekerDto.getAge().intValue());
        assertSame(appointmentDetailList, actualJobSeekerDto.getAppointmentDetailList());
        assertEquals("Contact No", actualJobSeekerDto.getContactNo());
        assertEquals("jane.doe@example.org", actualJobSeekerDto.getEmail());
        assertEquals("Id No", actualJobSeekerDto.getIdNo());
        assertEquals(1L, actualJobSeekerDto.getJobSeekerId().longValue());
        assertEquals("Name", actualJobSeekerDto.getName());
        assertEquals("GB", actualJobSeekerDto.getPreferCountry());
        assertEquals("Prefer Job Type", actualJobSeekerDto.getPreferJobType());
        assertEquals(
                "JobSeekerDto(jobSeekerId=1, name=Name, email=jane.doe@example.org, idNo=Id No, contactNo=Contact No,"
                        + " preferJobType=Prefer Job Type, preferCountry=GB, age=1, appointmentDetailList=[])",
                actualToStringResult);
    }

    @Test
    void testEquals() {
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
        assertNotEquals(jobSeekerDto, null);
    }

    @Test
    void testEquals2() {
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
        assertNotEquals(jobSeekerDto, "Different type to JobSeekerDto");
    }

    @Test
    void testEquals3() {
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
        assertEquals(jobSeekerDto, jobSeekerDto);
        int expectedHashCodeResult = jobSeekerDto.hashCode();
        assertEquals(expectedHashCodeResult, jobSeekerDto.hashCode());
    }

    @Test
    void testEquals4() {
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

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertEquals(jobSeekerDto, jobSeekerDto2);
        int expectedHashCodeResult = jobSeekerDto.hashCode();
        assertEquals(expectedHashCodeResult, jobSeekerDto2.hashCode());
    }

    @Test
    void testEquals5() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(3);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals6() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(null);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals7() {
        ArrayList<AppointmentDetail> appointmentDetailList = new ArrayList<>();
        appointmentDetailList.add(new AppointmentDetail());

        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(appointmentDetailList);
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals8() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Name");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals9() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo(null);
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals10() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("john.smith@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals11() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail(null);
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals12() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Name");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals13() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo(null);
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals14() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(2L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals15() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(null);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals16() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("jane.doe@example.org");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals17() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName(null);
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals18() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GBR");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals19() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry(null);
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals20() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Name");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals21() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType(null);

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }

    @Test
    void testEquals22() {
        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(null);
        jobSeekerDto.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(null);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertEquals(jobSeekerDto, jobSeekerDto2);
        int expectedHashCodeResult = jobSeekerDto.hashCode();
        assertEquals(expectedHashCodeResult, jobSeekerDto2.hashCode());
    }

    @Test
    void testEquals23() {
        ArrayList<AppointmentDetail> appointmentDetailList = new ArrayList<>();
        appointmentDetailList.add(mock(AppointmentDetail.class));

        JobSeekerDto jobSeekerDto = new JobSeekerDto();
        jobSeekerDto.setAge(1);
        jobSeekerDto.setAppointmentDetailList(appointmentDetailList);
        jobSeekerDto.setContactNo("Contact No");
        jobSeekerDto.setEmail("jane.doe@example.org");
        jobSeekerDto.setIdNo("Id No");
        jobSeekerDto.setJobSeekerId(1L);
        jobSeekerDto.setName("Name");
        jobSeekerDto.setPreferCountry("GB");
        jobSeekerDto.setPreferJobType("Prefer Job Type");

        JobSeekerDto jobSeekerDto2 = new JobSeekerDto();
        jobSeekerDto2.setAge(1);
        jobSeekerDto2.setAppointmentDetailList(new ArrayList<>());
        jobSeekerDto2.setContactNo("Contact No");
        jobSeekerDto2.setEmail("jane.doe@example.org");
        jobSeekerDto2.setIdNo("Id No");
        jobSeekerDto2.setJobSeekerId(1L);
        jobSeekerDto2.setName("Name");
        jobSeekerDto2.setPreferCountry("GB");
        jobSeekerDto2.setPreferJobType("Prefer Job Type");
        assertNotEquals(jobSeekerDto, jobSeekerDto2);
    }
}

