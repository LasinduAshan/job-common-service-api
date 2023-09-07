package com.job.common.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class JobSeekerTest {
    @Test
    void testConstructor() {
        JobSeeker actualJobSeeker = new JobSeeker();
        actualJobSeeker.setAge(1);
        ArrayList<AppointmentDetail> appointmentDetailList = new ArrayList<>();
        actualJobSeeker.setAppointmentDetailList(appointmentDetailList);
        actualJobSeeker.setContactNo("Contact No");
        actualJobSeeker.setEmail("jane.doe@example.org");
        actualJobSeeker.setIdNo("Id No");
        actualJobSeeker.setJobSeekerId(1L);
        actualJobSeeker.setName("Name");
        actualJobSeeker.setPreferCountry("GB");
        actualJobSeeker.setPreferJobType("Prefer Job Type");
        assertEquals(1, actualJobSeeker.getAge().intValue());
        assertSame(appointmentDetailList, actualJobSeeker.getAppointmentDetailList());
        assertEquals("Contact No", actualJobSeeker.getContactNo());
        assertEquals("jane.doe@example.org", actualJobSeeker.getEmail());
        assertEquals("Id No", actualJobSeeker.getIdNo());
        assertEquals(1L, actualJobSeeker.getJobSeekerId().longValue());
        assertEquals("Name", actualJobSeeker.getName());
        assertEquals("GB", actualJobSeeker.getPreferCountry());
        assertEquals("Prefer Job Type", actualJobSeeker.getPreferJobType());
    }
}

