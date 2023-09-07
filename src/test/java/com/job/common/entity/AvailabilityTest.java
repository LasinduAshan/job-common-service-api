package com.job.common.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.job.common.dto.AvailabilityDto;
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

@ContextConfiguration(classes = {Availability.class})
@ExtendWith(SpringExtension.class)
class AvailabilityTest {
    @Autowired
    private Availability availability;

    @Test
    void testToDto() {
        AvailabilityDto actualToDtoResult = availability.toDto(new ModelMapper());
        assertNull(actualToDtoResult.getAvailabilityId());
        assertNull(actualToDtoResult.getTimeSlots());
        assertNull(actualToDtoResult.getStartMinutes());
        assertNull(actualToDtoResult.getStartHour());
        assertEquals(RecordStatus.ACTIVE, actualToDtoResult.getRecordStatus());
        assertNull(actualToDtoResult.getModifiedDate());
        assertNull(actualToDtoResult.getIsWorkDay());
        assertNull(actualToDtoResult.getEndMinutes());
        assertNull(actualToDtoResult.getEndHour());
        assertNull(actualToDtoResult.getDay());
        assertNull(actualToDtoResult.getCreatedDate());
    }
    @Test
    void testToDto2() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.<Object, Object>addConverter(mock(Converter.class));
        AvailabilityDto actualToDtoResult = availability.toDto(modelMapper);
        assertNull(actualToDtoResult.getAvailabilityId());
        assertNull(actualToDtoResult.getTimeSlots());
        assertNull(actualToDtoResult.getStartMinutes());
        assertNull(actualToDtoResult.getStartHour());
        assertEquals(RecordStatus.ACTIVE, actualToDtoResult.getRecordStatus());
        assertNull(actualToDtoResult.getModifiedDate());
        assertNull(actualToDtoResult.getIsWorkDay());
        assertNull(actualToDtoResult.getEndMinutes());
        assertNull(actualToDtoResult.getEndHour());
        assertNull(actualToDtoResult.getDay());
        assertNull(actualToDtoResult.getCreatedDate());
    }
    @Test
    void testToDto3() {
        ModelMapper modelMapper = mock(ModelMapper.class);
        AvailabilityDto availabilityDto = new AvailabilityDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AvailabilityDto>>any())).thenReturn(availabilityDto);
        doNothing().when(modelMapper).addConverter(Mockito.<Converter<Object, Object>>any());
        modelMapper.<Object, Object>addConverter(mock(Converter.class));
        assertSame(availabilityDto, availability.toDto(modelMapper));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<AvailabilityDto>>any());
        verify(modelMapper).addConverter(Mockito.<Converter<Object, Object>>any());
    }
}

