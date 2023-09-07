package com.job.common.entity;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.job.common.dto.AppointmentDetailDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppointmentDetail.class})
@ExtendWith(SpringExtension.class)
class AppointmentDetailTest {
    @Autowired
    private AppointmentDetail appointmentDetail;

    @Test
    void testToDto() {
        ModelMapper modelMapper = mock(ModelMapper.class);
        AppointmentDetailDto appointmentDetailDto = new AppointmentDetailDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AppointmentDetailDto>>any()))
                .thenReturn(appointmentDetailDto);
        assertSame(appointmentDetailDto, appointmentDetail.toDto(modelMapper));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<AppointmentDetailDto>>any());
    }
}

