package com.job.common.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.job.common.entity.AppointmentDetail;
import com.job.common.enums.RecordStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppointmentDetailDto.class})
@ExtendWith(SpringExtension.class)
class AppointmentDetailDtoTest {
    @Autowired
    private AppointmentDetailDto appointmentDetailDto;

    @Test
    void testToEntity() {
        AppointmentDetail actualToEntityResult = appointmentDetailDto.toEntity(new ModelMapper());
        assertNull(actualToEntityResult.getAppointmentId());
        assertNull(actualToEntityResult.getTime());
        assertNull(actualToEntityResult.getSpecialNote());
        assertNull(actualToEntityResult.getRejectReason());
        assertEquals(RecordStatus.ACTIVE, actualToEntityResult.getRecordStatus());
        assertNull(actualToEntityResult.getModifiedDate());
        assertNull(actualToEntityResult.getDate());
        assertNull(actualToEntityResult.getCreatedDate());
        assertNull(actualToEntityResult.getAppointmentStatus());
    }

    @Test
    void testToEntity2() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(mock(Converter.class));
        AppointmentDetail actualToEntityResult = appointmentDetailDto.toEntity(modelMapper);
        assertNull(actualToEntityResult.getAppointmentId());
        assertNull(actualToEntityResult.getTime());
        assertNull(actualToEntityResult.getSpecialNote());
        assertNull(actualToEntityResult.getRejectReason());
        assertEquals(RecordStatus.ACTIVE, actualToEntityResult.getRecordStatus());
        assertNull(actualToEntityResult.getModifiedDate());
        assertNull(actualToEntityResult.getDate());
        assertNull(actualToEntityResult.getCreatedDate());
        assertNull(actualToEntityResult.getAppointmentStatus());
    }

    @Test
    void testToEntity3() {
        ModelMapper modelMapper = mock(ModelMapper.class);
        AppointmentDetail appointmentDetail = new AppointmentDetail();
        when(modelMapper.map(Mockito.any(), Mockito.<Class<AppointmentDetail>>any()))
                .thenReturn(appointmentDetail);
        doNothing().when(modelMapper).addConverter(Mockito.any());
        modelMapper.addConverter(mock(Converter.class));
        assertSame(appointmentDetail, appointmentDetailDto.toEntity(modelMapper));
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<AppointmentDetail>>any());
        verify(modelMapper).addConverter(Mockito.any());
    }
}

