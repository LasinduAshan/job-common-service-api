package com.job.common.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.job.common.entity.AppointmentDetail;
import com.job.common.entity.Consultant;
import com.job.common.enums.RecordStatus;

import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ConsultantDto.class})
@ExtendWith(SpringExtension.class)
class ConsultantDtoTest {
    @Autowired
    private ConsultantDto consultantDto;

    @Test
    void testToEntity() {
        Consultant actualToEntityResult = consultantDto.toEntity(new ModelMapper());
        List<AppointmentDetail> appointmentDetailList = actualToEntityResult.getAppointmentDetailList();
        assertTrue(appointmentDetailList.isEmpty());
        assertEquals(RecordStatus.ACTIVE, actualToEntityResult.getRecordStatus());
        assertNull(actualToEntityResult.getModifiedDate());
        assertNull(actualToEntityResult.getLastName());
        assertNull(actualToEntityResult.getJobType());
        assertNull(actualToEntityResult.getIdNo());
        assertNull(actualToEntityResult.getFirstName());
        assertNull(actualToEntityResult.getEmail());
        assertNull(actualToEntityResult.getCreatedDate());
        assertNull(actualToEntityResult.getCountry());
        assertNull(actualToEntityResult.getContactNo());
        assertNull(actualToEntityResult.getConsultantId());
        assertEquals(appointmentDetailList, actualToEntityResult.getAvailabilityList());
    }

    @Test
    void testToEntity2() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(mock(Converter.class));
        Consultant actualToEntityResult = consultantDto.toEntity(modelMapper);
        List<AppointmentDetail> appointmentDetailList = actualToEntityResult.getAppointmentDetailList();
        assertTrue(appointmentDetailList.isEmpty());
        assertEquals(RecordStatus.ACTIVE, actualToEntityResult.getRecordStatus());
        assertNull(actualToEntityResult.getModifiedDate());
        assertNull(actualToEntityResult.getLastName());
        assertNull(actualToEntityResult.getJobType());
        assertNull(actualToEntityResult.getIdNo());
        assertNull(actualToEntityResult.getFirstName());
        assertNull(actualToEntityResult.getEmail());
        assertNull(actualToEntityResult.getCreatedDate());
        assertNull(actualToEntityResult.getCountry());
        assertNull(actualToEntityResult.getContactNo());
        assertNull(actualToEntityResult.getConsultantId());
        assertEquals(appointmentDetailList, actualToEntityResult.getAvailabilityList());
    }

    @Test
    void testToEntity3() {
        ModelMapper modelMapper = mock(ModelMapper.class);
        Consultant consultant = new Consultant();
        when(modelMapper.map(Mockito.any(), Mockito.<Class<Consultant>>any())).thenReturn(consultant);
        doNothing().when(modelMapper).addConverter(Mockito.any());
        modelMapper.addConverter(mock(Converter.class));
        assertSame(consultant, consultantDto.toEntity(modelMapper));
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<Consultant>>any());
        verify(modelMapper).addConverter(Mockito.any());
    }
}

