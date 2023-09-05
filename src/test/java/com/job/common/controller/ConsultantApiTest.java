package com.job.common.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.job.common.dto.AvailabilityDto;
import com.job.common.dto.ConsultantDto;
import com.job.common.dto.ListItemDto;
import com.job.common.entity.Availability;
import com.job.common.entity.Consultant;
import com.job.common.entity.auth.User;
import com.job.common.enums.RecordStatus;
import com.job.common.repository.AppointmentDetailRepository;
import com.job.common.repository.AvailabilityRepository;
import com.job.common.repository.ConsultantRepository;
import com.job.common.repository.auth.TokenRepository;
import com.job.common.repository.auth.UserRepository;
import com.job.common.service.ConsultantService;
import com.job.common.service.impl.ConsultantServiceImpl;
import com.job.common.util.AuthenticationUtil;
import com.job.common.util.JwtUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class ConsultantApiTest {

    @Test
    void testSaveConsultant() throws Exception {

        ConsultantService consultantService = mock(ConsultantService.class);
        when(consultantService.save(Mockito.any())).thenReturn(new ConsultantDto());
        ConsultantApi consultantApi = new ConsultantApi(consultantService);
        ConsultantDto consultantDto = new ConsultantDto();
        ResponseEntity<ConsultantDto> actualSaveConsultantResult = consultantApi.saveConsultant(consultantDto);
        assertEquals(consultantDto, actualSaveConsultantResult.getBody());
        assertTrue(actualSaveConsultantResult.getHeaders().isEmpty());
        assertEquals(200, actualSaveConsultantResult.getStatusCodeValue());
        verify(consultantService).save(Mockito.any());
    }

    @Test
    void testUpdateConsultant() throws Exception {

        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        when(consultantRepository.save(Mockito.any())).thenReturn(new Consultant());
        when(consultantRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Consultant()));

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepository repository = mock(UserRepository.class);
        TokenRepository tokenRepository = mock(TokenRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationUtil authenticationUtil = new AuthenticationUtil(repository, tokenRepository, passwordEncoder,
                new JwtUtil(), authenticationManager);

        AvailabilityRepository availabilityRepository = mock(AvailabilityRepository.class);
        when(availabilityRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        TokenRepository tokenRepository2 = mock(TokenRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        ConsultantApi consultantApi = new ConsultantApi(new ConsultantServiceImpl(tokenRepository2, userRepository,
                modelMapper, new ObjectMapper(), consultantRepository, authenticationUtil, availabilityRepository,
                mock(AppointmentDetailRepository.class)));
        ResponseEntity<ConsultantDto> actualUpdateConsultantResult = consultantApi.updateConsultant(new ConsultantDto());
        assertTrue(actualUpdateConsultantResult.hasBody());
        assertTrue(actualUpdateConsultantResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateConsultantResult.getStatusCodeValue());
        assertNull(actualUpdateConsultantResult.getBody().getConsultantId());
        verify(consultantRepository).save(Mockito.any());
        verify(consultantRepository).findById(Mockito.<Long>any());
        verify(availabilityRepository).saveAll(Mockito.any());
    }

    @Test
    void testGetConsultantDetailById() {

        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        when(consultantRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Consultant()));

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepository repository = mock(UserRepository.class);
        TokenRepository tokenRepository = mock(TokenRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationUtil authenticationUtil = new AuthenticationUtil(repository, tokenRepository, passwordEncoder,
                new JwtUtil(), authenticationManager);

        TokenRepository tokenRepository2 = mock(TokenRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        ResponseEntity<ConsultantDto> actualConsultantDetailById = (new ConsultantApi(new ConsultantServiceImpl(
                tokenRepository2, userRepository, modelMapper, new ObjectMapper(), consultantRepository, authenticationUtil,
                mock(AvailabilityRepository.class), mock(AppointmentDetailRepository.class)))).getConsultantDetailById(1L);
        assertTrue(actualConsultantDetailById.hasBody());
        assertTrue(actualConsultantDetailById.getHeaders().isEmpty());
        assertEquals(200, actualConsultantDetailById.getStatusCodeValue());
        ConsultantDto body = actualConsultantDetailById.getBody();
        assertNull(body.getContactNo());
        assertNull(body.getLastName());
        assertNull(body.getJobType());
        assertNull(body.getIdNo());
        assertNull(body.getFirstName());
        assertNull(body.getEmail());
        assertNull(body.getCreatedDate());
        assertNull(body.getCountry());
        assertNull(body.getConsultantId());
        List<AvailabilityDto> availabilityDtoList = body.getAvailabilityDtoList();
        assertTrue(availabilityDtoList.isEmpty());
        assertEquals(availabilityDtoList, body.getAppointmentDetailDtoList());
        assertEquals(RecordStatus.ACTIVE, body.getRecordStatus());
        assertNull(body.getModifiedDate());
        verify(consultantRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testGetConsultantDetailByEmail() {

        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        when(consultantRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new Consultant()));

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepository repository = mock(UserRepository.class);
        TokenRepository tokenRepository = mock(TokenRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationUtil authenticationUtil = new AuthenticationUtil(repository, tokenRepository, passwordEncoder,
                new JwtUtil(), authenticationManager);

        TokenRepository tokenRepository2 = mock(TokenRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        ResponseEntity<ConsultantDto> actualConsultantDetailByEmail = (new ConsultantApi(new ConsultantServiceImpl(
                tokenRepository2, userRepository, modelMapper, new ObjectMapper(), consultantRepository, authenticationUtil,
                mock(AvailabilityRepository.class), mock(AppointmentDetailRepository.class))))
                .getConsultantDetailByEmail("jane.doe@example.com");
        assertTrue(actualConsultantDetailByEmail.hasBody());
        assertTrue(actualConsultantDetailByEmail.getHeaders().isEmpty());
        assertEquals(200, actualConsultantDetailByEmail.getStatusCodeValue());
        ConsultantDto body = actualConsultantDetailByEmail.getBody();
        assertNull(body.getContactNo());
        assertNull(body.getLastName());
        assertNull(body.getJobType());
        assertNull(body.getIdNo());
        assertNull(body.getFirstName());
        assertNull(body.getEmail());
        assertNull(body.getCreatedDate());
        assertNull(body.getCountry());
        assertNull(body.getConsultantId());
        List<AvailabilityDto> availabilityDtoList = body.getAvailabilityDtoList();
        assertTrue(availabilityDtoList.isEmpty());
        assertEquals(availabilityDtoList, body.getAppointmentDetailDtoList());
        assertEquals(RecordStatus.ACTIVE, body.getRecordStatus());
        assertNull(body.getModifiedDate());
        verify(consultantRepository).findByEmail(Mockito.any());
    }

    @Test
    void testGetAllConsultants() {

        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        when(consultantRepository.findAll()).thenReturn(new ArrayList<>());

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepository repository = mock(UserRepository.class);
        TokenRepository tokenRepository = mock(TokenRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationUtil authenticationUtil = new AuthenticationUtil(repository, tokenRepository, passwordEncoder,
                new JwtUtil(), authenticationManager);

        TokenRepository tokenRepository2 = mock(TokenRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        ResponseEntity<List<ConsultantDto>> actualAllConsultants = (new ConsultantApi(new ConsultantServiceImpl(
                tokenRepository2, userRepository, modelMapper, new ObjectMapper(), consultantRepository, authenticationUtil,
                mock(AvailabilityRepository.class), mock(AppointmentDetailRepository.class)))).getAllConsultants();
        assertTrue(actualAllConsultants.hasBody());
        assertEquals(200, actualAllConsultants.getStatusCodeValue());
        assertTrue(actualAllConsultants.getHeaders().isEmpty());
        verify(consultantRepository).findAll();
    }

    @Test
    void testGetAvailabilityTimeSlots() throws JsonProcessingException {

        ConsultantServiceImpl consultantService = mock(ConsultantServiceImpl.class);
        when(
                consultantService.getAvailabilityTimeSlots(Mockito.any(), Mockito.any(), Mockito.<Long>any()))
                .thenReturn(new ArrayList<>());
        ResponseEntity<List<ListItemDto>> actualAvailabilityTimeSlots = (new ConsultantApi(consultantService))
                .getAvailabilityTimeSlots("2020-03-01", "Day", 1L);
        assertTrue(actualAvailabilityTimeSlots.hasBody());
        assertEquals(200, actualAvailabilityTimeSlots.getStatusCodeValue());
        assertTrue(actualAvailabilityTimeSlots.getHeaders().isEmpty());
        verify(consultantService).getAvailabilityTimeSlots(Mockito.any(), Mockito.any(),
                Mockito.<Long>any());
    }

    @Test
    void testDeleteConsultantDetailById() {

        TokenRepository tokenRepository = mock(TokenRepository.class);
        when(tokenRepository.deleteAllByUserId(Mockito.<Integer>any())).thenReturn(1);
        UserRepository userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).delete(Mockito.any());
        when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new User()));
        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        doNothing().when(consultantRepository).delete(Mockito.any());
        when(consultantRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Consultant()));

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepository repository = mock(UserRepository.class);
        TokenRepository tokenRepository2 = mock(TokenRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationUtil authenticationUtil = new AuthenticationUtil(repository, tokenRepository2, passwordEncoder,
                new JwtUtil(), authenticationManager);

        ModelMapper modelMapper = new ModelMapper();
        ResponseEntity<ConsultantDto> actualDeleteConsultantDetailByIdResult = (new ConsultantApi(
                new ConsultantServiceImpl(tokenRepository, userRepository, modelMapper, new ObjectMapper(),
                        consultantRepository, authenticationUtil, mock(AvailabilityRepository.class),
                        mock(AppointmentDetailRepository.class)))).deleteConsultantDetailById(1L);
        assertTrue(actualDeleteConsultantDetailByIdResult.hasBody());
        assertTrue(actualDeleteConsultantDetailByIdResult.getHeaders().isEmpty());
        assertEquals(200, actualDeleteConsultantDetailByIdResult.getStatusCodeValue());
        ConsultantDto body = actualDeleteConsultantDetailByIdResult.getBody();
        assertNull(body.getContactNo());
        assertNull(body.getLastName());
        assertNull(body.getJobType());
        assertNull(body.getIdNo());
        assertNull(body.getFirstName());
        assertNull(body.getEmail());
        assertNull(body.getCreatedDate());
        assertNull(body.getCountry());
        assertNull(body.getConsultantId());
        List<AvailabilityDto> availabilityDtoList = body.getAvailabilityDtoList();
        assertTrue(availabilityDtoList.isEmpty());
        assertEquals(availabilityDtoList, body.getAppointmentDetailDtoList());
        assertEquals(RecordStatus.ACTIVE, body.getRecordStatus());
        assertNull(body.getModifiedDate());
        verify(tokenRepository).deleteAllByUserId(Mockito.<Integer>any());
        verify(userRepository).findByEmail(Mockito.any());
        verify(userRepository).delete(Mockito.any());
        verify(consultantRepository).findById(Mockito.<Long>any());
        verify(consultantRepository).delete(Mockito.any());
    }
}

