package com.job.common.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.job.common.entity.Availability;
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

@ContextConfiguration(classes = {AvailabilityDto.class})
@ExtendWith(SpringExtension.class)
class AvailabilityDtoTest {
    @Autowired
    private AvailabilityDto availabilityDto;

    @Test
    void testToEntity() {
        Availability actualToEntityResult = availabilityDto.toEntity(new ModelMapper());
        assertNull(actualToEntityResult.getAvailabilityId());
        assertNull(actualToEntityResult.getTimeSlots());
        assertNull(actualToEntityResult.getStartMinutes());
        assertNull(actualToEntityResult.getStartHour());
        assertEquals(RecordStatus.ACTIVE, actualToEntityResult.getRecordStatus());
        assertNull(actualToEntityResult.getModifiedDate());
        assertNull(actualToEntityResult.getIsWorkDay());
        assertNull(actualToEntityResult.getEndMinutes());
        assertNull(actualToEntityResult.getEndHour());
        assertNull(actualToEntityResult.getDay());
        assertNull(actualToEntityResult.getCreatedDate());
    }

    @Test
    void testToEntity2() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(mock(Converter.class));
        Availability actualToEntityResult = availabilityDto.toEntity(modelMapper);
        assertNull(actualToEntityResult.getAvailabilityId());
        assertNull(actualToEntityResult.getTimeSlots());
        assertNull(actualToEntityResult.getStartMinutes());
        assertNull(actualToEntityResult.getStartHour());
        assertEquals(RecordStatus.ACTIVE, actualToEntityResult.getRecordStatus());
        assertNull(actualToEntityResult.getModifiedDate());
        assertNull(actualToEntityResult.getIsWorkDay());
        assertNull(actualToEntityResult.getEndMinutes());
        assertNull(actualToEntityResult.getEndHour());
        assertNull(actualToEntityResult.getDay());
        assertNull(actualToEntityResult.getCreatedDate());
    }

    @Test
    void testToEntity3() {
        ModelMapper modelMapper = mock(ModelMapper.class);
        Availability availability = new Availability();
        when(modelMapper.map(Mockito.any(), Mockito.<Class<Availability>>any())).thenReturn(availability);
        doNothing().when(modelMapper).addConverter(Mockito.any());
        modelMapper.addConverter(mock(Converter.class));
        assertSame(availability, availabilityDto.toEntity(modelMapper));
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<Availability>>any());
        verify(modelMapper).addConverter(Mockito.any());
    }
}

