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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.job.common.dto.ConsultantDto;
import com.job.common.dto.auth.RegisterRequestDto;
import com.job.common.entity.Availability;
import com.job.common.entity.Consultant;
import com.job.common.entity.auth.User;
import com.job.common.enums.Role;
import com.job.common.exception.AlreadyExistsException;
import com.job.common.exception.RecordNotFoundException;
import com.job.common.exception.RequiredValueException;
import com.job.common.repository.AppointmentDetailRepository;
import com.job.common.repository.AvailabilityRepository;
import com.job.common.repository.ConsultantRepository;
import com.job.common.repository.auth.TokenRepository;
import com.job.common.repository.auth.UserRepository;
import com.job.common.util.AuthenticationUtil;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {ConsultantServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ConsultantServiceImplTest {
    @MockBean
    private AppointmentDetailRepository appointmentDetailRepository;

    @MockBean
    private AuthenticationUtil authenticationUtil;

    @MockBean
    private AvailabilityRepository availabilityRepository;

    @MockBean
    private ConsultantRepository consultantRepository;

    @Autowired
    private ConsultantServiceImpl consultantServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private ObjectMapper objectMapper;

    @MockBean
    private TokenRepository tokenRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testSave() throws Exception {
        assertThrows(RequiredValueException.class, () -> consultantServiceImpl.save(null));
    }

    @Test
    void testSave2() throws Exception {
        when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new User()));
        when(modelMapper.map(Mockito.any(), Mockito.any()))
                .thenReturn(new RegisterRequestDto("Jane", "Doe", "jane.doe@example.com", "iloveyou", Role.CONSULTANT));
        when(consultantRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new Consultant()));
        ConsultantDto consultantDto = mock(ConsultantDto.class);
        when(consultantDto.getEmail()).thenReturn("jane.doe@example.com");
        when(consultantDto.toEntity(Mockito.any())).thenReturn(new Consultant());
        assertThrows(AlreadyExistsException.class, () -> consultantServiceImpl.save(consultantDto));
        verify(userRepository).findByEmail(Mockito.any());
        verify(modelMapper).map(Mockito.any(), Mockito.any());
        verify(consultantRepository).findByEmail(Mockito.any());
        verify(consultantDto).toEntity(Mockito.any());
        verify(consultantDto, atLeast(1)).getEmail();
    }

    @Test
    void testSave3() throws Exception {
        when(modelMapper.map(Mockito.any(), Mockito.any()))
                .thenReturn(new RegisterRequestDto("Jane", "Doe", "jane.doe@example.com", "iloveyou", Role.CONSULTANT));
        ConsultantDto consultantDto = mock(ConsultantDto.class);
        when(consultantDto.getEmail()).thenThrow(new RequiredValueException("An error occurred"));
        when(consultantDto.toEntity(Mockito.any())).thenReturn(new Consultant());
        assertThrows(RequiredValueException.class, () -> consultantServiceImpl.save(consultantDto));
        verify(modelMapper).map(Mockito.any(), Mockito.any());
        verify(consultantDto).toEntity(Mockito.any());
        verify(consultantDto).getEmail();
    }


    @Test
    void testSave4() throws Exception {
        when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new User()));
        when(modelMapper.map(Mockito.any(), Mockito.any()))
                .thenReturn(new RegisterRequestDto("Jane", "Doe", "jane.doe@example.com", "iloveyou", Role.CONSULTANT));
        when(consultantRepository.findByEmail(Mockito.any())).thenReturn(Optional.empty());
        ConsultantDto consultantDto = mock(ConsultantDto.class);
        when(consultantDto.getEmail()).thenReturn("jane.doe@example.com");
        when(consultantDto.toEntity(Mockito.any())).thenReturn(new Consultant());
        assertThrows(AlreadyExistsException.class, () -> consultantServiceImpl.save(consultantDto));
        verify(userRepository).findByEmail(Mockito.any());
        verify(modelMapper).map(Mockito.any(), Mockito.any());
        verify(consultantRepository).findByEmail(Mockito.any());
        verify(consultantDto).toEntity(Mockito.any());
        verify(consultantDto, atLeast(1)).getEmail();
    }

    @Test
    void testUpdate() throws Exception {
        when(modelMapper.map(Mockito.any(), Mockito.<Class<Consultant>>any())).thenReturn(new Consultant());
        when(consultantRepository.save(Mockito.any())).thenReturn(new Consultant());
        when(consultantRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Consultant()));
        when(availabilityRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        ConsultantDto consultantDto = new ConsultantDto();
        ConsultantDto actualUpdateResult = consultantServiceImpl.update(consultantDto);
        assertSame(consultantDto, actualUpdateResult);
        assertNull(actualUpdateResult.getConsultantId());
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<Consultant>>any());
        verify(consultantRepository).save(Mockito.any());
        verify(consultantRepository).findById(Mockito.<Long>any());
        verify(availabilityRepository).saveAll(Mockito.any());
    }

    @Test
    void testUpdate2() throws Exception {
        when(modelMapper.map(Mockito.any(), Mockito.<Class<Consultant>>any())).thenReturn(new Consultant());
        when(consultantRepository.save(Mockito.any())).thenReturn(new Consultant());
        when(consultantRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Consultant()));
        when(availabilityRepository.saveAll(Mockito.any()))
                .thenThrow(new RequiredValueException("An error occurred"));
        assertThrows(RequiredValueException.class, () -> consultantServiceImpl.update(new ConsultantDto()));
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<Consultant>>any());
        verify(consultantRepository).save(Mockito.any());
        verify(consultantRepository).findById(Mockito.<Long>any());
        verify(availabilityRepository).saveAll(Mockito.any());
    }

    @Test
    void testUpdate3() throws Exception {
        when(modelMapper.map(Mockito.any(), Mockito.<Class<Consultant>>any())).thenReturn(new Consultant());
        Consultant consultant = mock(Consultant.class);
        when(consultant.getConsultantId()).thenReturn(1L);
        when(consultantRepository.save(Mockito.any())).thenReturn(consultant);
        when(consultantRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Consultant()));
        when(availabilityRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        ConsultantDto consultantDto = new ConsultantDto();
        ConsultantDto actualUpdateResult = consultantServiceImpl.update(consultantDto);
        assertSame(consultantDto, actualUpdateResult);
        assertEquals(1L, actualUpdateResult.getConsultantId().longValue());
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<Consultant>>any());
        verify(consultantRepository).save(Mockito.any());
        verify(consultantRepository).findById(Mockito.<Long>any());
        verify(consultant).getConsultantId();
        verify(availabilityRepository).saveAll(Mockito.any());
    }


    @Test
    void testDelete() {
        when(tokenRepository.deleteAllByUserId(Mockito.<Integer>any())).thenReturn(1);
        doNothing().when(userRepository).delete(Mockito.any());
        when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new User()));
        ConsultantDto consultantDto = new ConsultantDto();
        when(modelMapper.map(Mockito.any(), Mockito.<Class<ConsultantDto>>any())).thenReturn(consultantDto);
        doNothing().when(consultantRepository).delete(Mockito.any());
        when(consultantRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Consultant()));
        assertSame(consultantDto, consultantServiceImpl.delete(1L));
        verify(tokenRepository).deleteAllByUserId(Mockito.<Integer>any());
        verify(userRepository).findByEmail(Mockito.any());
        verify(userRepository).delete(Mockito.any());
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<ConsultantDto>>any());
        verify(consultantRepository).findById(Mockito.<Long>any());
        verify(consultantRepository).delete(Mockito.any());
    }

    @Test
    void testGetConsultantDetailById() {
        ConsultantDto consultantDto = new ConsultantDto();
        when(modelMapper.map(Mockito.any(), Mockito.<Class<ConsultantDto>>any())).thenReturn(consultantDto);
        when(consultantRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Consultant()));
        assertSame(consultantDto, consultantServiceImpl.getConsultantDetailById(1L));
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<ConsultantDto>>any());
        verify(consultantRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testGetConsultantDetailById2() {
        when(modelMapper.map(Mockito.any(), Mockito.<Class<ConsultantDto>>any()))
                .thenThrow(new RequiredValueException("An error occurred"));
        when(consultantRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Consultant()));
        assertThrows(RequiredValueException.class, () -> consultantServiceImpl.getConsultantDetailById(1L));
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<ConsultantDto>>any());
        verify(consultantRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testGetConsultantDetailByEmail() {
        ConsultantDto consultantDto = new ConsultantDto();
        when(modelMapper.map(Mockito.any(), Mockito.<Class<ConsultantDto>>any())).thenReturn(consultantDto);
        when(consultantRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new Consultant()));
        assertSame(consultantDto, consultantServiceImpl.getConsultantDetailByEmail("jane.doe@example.com"));
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<ConsultantDto>>any());
        verify(consultantRepository).findByEmail(Mockito.any());
    }

    @Test
    void testGetConsultantDetailByEmail2() {
        when(modelMapper.map(Mockito.any(), Mockito.<Class<ConsultantDto>>any()))
                .thenThrow(new RequiredValueException("An error occurred"));
        when(consultantRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new Consultant()));
        assertThrows(RequiredValueException.class,
                () -> consultantServiceImpl.getConsultantDetailByEmail("jane.doe@example.com"));
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<ConsultantDto>>any());
        verify(consultantRepository).findByEmail(Mockito.any());
    }

    @Test
    void testGetAllConsultantDetailList() {
        when(consultantRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(consultantServiceImpl.getAllConsultantDetailList().isEmpty());
        verify(consultantRepository).findAll();
    }

    @Test
    void testGetAllConsultantDetailList2() {
        when(modelMapper.map(Mockito.any(), Mockito.<Class<ConsultantDto>>any())).thenReturn(new ConsultantDto());

        ArrayList<Consultant> consultantList = new ArrayList<>();
        consultantList.add(new Consultant());
        when(consultantRepository.findAll()).thenReturn(consultantList);
        assertEquals(1, consultantServiceImpl.getAllConsultantDetailList().size());
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<ConsultantDto>>any());
        verify(consultantRepository).findAll();
    }

    @Test
    void testGetAllConsultantDetailList3() {
        when(modelMapper.map(Mockito.any(), Mockito.<Class<ConsultantDto>>any())).thenReturn(new ConsultantDto());

        ArrayList<Consultant> consultantList = new ArrayList<>();
        consultantList.add(new Consultant());
        consultantList.add(new Consultant());
        when(consultantRepository.findAll()).thenReturn(consultantList);
        assertEquals(2, consultantServiceImpl.getAllConsultantDetailList().size());
        verify(modelMapper, atLeast(1)).map(Mockito.any(), Mockito.<Class<ConsultantDto>>any());
        verify(consultantRepository).findAll();
    }

}

