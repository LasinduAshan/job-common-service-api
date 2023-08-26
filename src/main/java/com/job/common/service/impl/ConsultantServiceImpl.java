package com.job.common.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.job.common.dto.AvailabilityDto;
import com.job.common.dto.ConsultantDto;
import com.job.common.dto.auth.RegisterRequestDto;
import com.job.common.entity.Availability;
import com.job.common.entity.Consultant;
import com.job.common.enums.Role;
import com.job.common.exception.RequiredValueException;
import com.job.common.repository.AvailabilityRepository;
import com.job.common.repository.ConsultantRepository;
import com.job.common.service.ConsultantService;
import com.job.common.util.AuthenticationUtil;
import com.job.common.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsultantServiceImpl implements ConsultantService {
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final ConsultantRepository consultantRepository;
    private final AuthenticationUtil authenticationUtil;
    private final AvailabilityRepository availabilityRepository;


    @Transactional
    @Override
    public ConsultantDto save(ConsultantDto consultantDto) throws Exception {

        if (null == consultantDto) {
            throw new RequiredValueException("Required Values are missing");
        }

        Consultant consultant = consultantDto.toEntity(modelMapper);
        RegisterRequestDto registerRequestDto = modelMapper.map(consultantDto, RegisterRequestDto.class);
        registerRequestDto.setRole(Role.CONSULTANT);

        try {
            authenticationUtil.register(registerRequestDto);
            Consultant saveConsultant = consultantRepository.save(consultant);

            List<Availability> availabilityList = new ArrayList<>();

            for (AvailabilityDto availabilityDto : consultantDto.getAvailabilityDtoList()) {
                Availability availability = availabilityDto.toEntity(modelMapper);
                availability.setConsultant(saveConsultant);
                availability.setTimeSlots(objectMapper.writeValueAsString(Utility
                        .generateValues(
                                availabilityDto.getStartHour().concat(":").concat(availabilityDto.getStartMinutes()),
                                availabilityDto.getEndHour().concat(":").concat(availabilityDto.getEndMinutes()))));

                availabilityList.add(availability);
            }
            availabilityRepository.saveAll(availabilityList);

            return saveConsultant.toDto(modelMapper);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ConsultantDto update(ConsultantDto consultantDto) throws Exception {

        try {
            Optional<Consultant> existConsultant = consultantRepository.findById(consultantDto.getConsultantId());
            if (existConsultant.isPresent()) {

                Consultant saveConsultant = consultantRepository.save(consultantDto.toEntity(modelMapper));

                List<Availability> availabilityList = new ArrayList<>();

                for (AvailabilityDto availabilityDto : consultantDto.getAvailabilityDtoList()) {

                    Availability availability = availabilityDto.toEntity(modelMapper);
                    availability.setConsultant(saveConsultant);
                    availability.setTimeSlots(objectMapper.writeValueAsString(Utility
                            .generateValues(
                                    availabilityDto.getStartHour().concat(":").concat(availabilityDto.getStartMinutes()),
                                    availabilityDto.getEndHour().concat(":").concat(availabilityDto.getEndMinutes()))));

                    availabilityList.add(availability);

                }
                availabilityRepository.saveAll(availabilityList);
                consultantDto.setConsultantId(saveConsultant.getConsultantId());
            }
            return consultantDto;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ConsultantDto delete(Long consultantId) {
        return null;
    }

    @Override
    public ConsultantDto getConsultantDetailById(Long consultantId) {
        return null;
    }

    @Override
    public List<ConsultantDto> getAllConsultantDetailList() {

        return consultantRepository.findAll()
                .stream()
                .map(consultant -> consultant.toDto(modelMapper))
                .toList();
    }
}
