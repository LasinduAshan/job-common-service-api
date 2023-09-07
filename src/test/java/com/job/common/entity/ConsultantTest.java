package com.job.common.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.job.common.dto.AppointmentDetailDto;
import com.job.common.dto.ConsultantDto;
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

@ContextConfiguration(classes = {Consultant.class})
@ExtendWith(SpringExtension.class)
class ConsultantTest {
    @Autowired
    private Consultant consultant;

    @Test
    void testToDto() {
        ConsultantDto actualToDtoResult = consultant.toDto(new ModelMapper());
        List<AppointmentDetailDto> appointmentDetailDtoList = actualToDtoResult.getAppointmentDetailDtoList();
        assertTrue(appointmentDetailDtoList.isEmpty());
        assertEquals(RecordStatus.ACTIVE, actualToDtoResult.getRecordStatus());
        assertNull(actualToDtoResult.getModifiedDate());
        assertNull(actualToDtoResult.getLastName());
        assertNull(actualToDtoResult.getJobType());
        assertNull(actualToDtoResult.getIdNo());
        assertNull(actualToDtoResult.getFirstName());
        assertNull(actualToDtoResult.getEmail());
        assertNull(actualToDtoResult.getCreatedDate());
        assertNull(actualToDtoResult.getCountry());
        assertNull(actualToDtoResult.getContactNo());
        assertNull(actualToDtoResult.getConsultantId());
        assertEquals(appointmentDetailDtoList, actualToDtoResult.getAvailabilityDtoList());
    }

    @Test
    void testToDto3() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(mock(Converter.class));
        ConsultantDto actualToDtoResult = consultant.toDto(modelMapper);
        List<AppointmentDetailDto> appointmentDetailDtoList = actualToDtoResult.getAppointmentDetailDtoList();
        assertTrue(appointmentDetailDtoList.isEmpty());
        assertEquals(RecordStatus.ACTIVE, actualToDtoResult.getRecordStatus());
        assertNull(actualToDtoResult.getModifiedDate());
        assertNull(actualToDtoResult.getLastName());
        assertNull(actualToDtoResult.getJobType());
        assertNull(actualToDtoResult.getIdNo());
        assertNull(actualToDtoResult.getFirstName());
        assertNull(actualToDtoResult.getEmail());
        assertNull(actualToDtoResult.getCreatedDate());
        assertNull(actualToDtoResult.getCountry());
        assertNull(actualToDtoResult.getContactNo());
        assertNull(actualToDtoResult.getConsultantId());
        assertEquals(appointmentDetailDtoList, actualToDtoResult.getAvailabilityDtoList());
    }

    @Test
    void testToDto4() {
        ModelMapper modelMapper = mock(ModelMapper.class);
        ConsultantDto consultantDto = new ConsultantDto();
        when(modelMapper.map(Mockito.any(), Mockito.<Class<ConsultantDto>>any())).thenReturn(consultantDto);
        doNothing().when(modelMapper).addConverter(Mockito.any());
        modelMapper.addConverter(mock(Converter.class));
        assertSame(consultantDto, consultant.toDto(modelMapper));
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<ConsultantDto>>any());
        verify(modelMapper).addConverter(Mockito.any());
    }
}

