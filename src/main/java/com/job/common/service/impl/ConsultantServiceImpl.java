package com.job.common.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.job.common.dto.AvailabilityDto;
import com.job.common.dto.ConsultantDto;
import com.job.common.dto.ListItemDto;
import com.job.common.dto.auth.RegisterRequestDto;
import com.job.common.entity.AppointmentDetail;
import com.job.common.entity.Availability;
import com.job.common.entity.Consultant;
import com.job.common.entity.auth.User;
import com.job.common.enums.AppointmentStatus;
import com.job.common.enums.Days;
import com.job.common.enums.Role;
import com.job.common.exception.AlreadyExistsException;
import com.job.common.exception.RecordNotFoundException;
import com.job.common.exception.RequiredValueException;
import com.job.common.repository.AppointmentDetailRepository;
import com.job.common.repository.AvailabilityRepository;
import com.job.common.repository.ConsultantRepository;
import com.job.common.repository.auth.TokenRepository;
import com.job.common.repository.auth.UserRepository;
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
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsultantServiceImpl implements ConsultantService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final ConsultantRepository consultantRepository;
    private final AuthenticationUtil authenticationUtil;
    private final AvailabilityRepository availabilityRepository;
    private final AppointmentDetailRepository appointmentDetailRepository;


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

            Optional<Consultant> consultantOptional = consultantRepository.findByEmail(consultantDto.getEmail());
            Optional<User> userOptional = userRepository.findByEmail(consultantDto.getEmail());
            if (consultantOptional.isPresent() || userOptional.isPresent()) {
                throw new AlreadyExistsException("This email already exists");
            }

            authenticationUtil.register(registerRequestDto);
            Consultant saveConsultant = consultantRepository.save(consultant);

            List<Availability> availabilityList = new ArrayList<>();

            for (AvailabilityDto availabilityDto : consultantDto.getAvailabilityDtoList()) {
                Availability availability = availabilityDto.toEntity(modelMapper);
                availability.setConsultant(saveConsultant);
                if (Boolean.TRUE.equals(availabilityDto.getIsWorkDay())) {
                    availability.setTimeSlots(objectMapper.writeValueAsString(Utility
                            .generateValues(
                                    availabilityDto.getStartHour().concat(":").concat(availabilityDto.getStartMinutes()),
                                    availabilityDto.getEndHour().concat(":").concat(availabilityDto.getEndMinutes()))));

                } else {
                    availability.setTimeSlots(null);
                }

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
                    if (Boolean.TRUE.equals(availabilityDto.getIsWorkDay())) {
                        availability.setTimeSlots(objectMapper.writeValueAsString(Utility
                                .generateValues(
                                        availabilityDto.getStartHour().concat(":").concat(availabilityDto.getStartMinutes()),
                                        availabilityDto.getEndHour().concat(":").concat(availabilityDto.getEndMinutes()))));
                    } else {
                        availability.setTimeSlots(null);
                    }
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

    @Transactional
    @Override
    public ConsultantDto delete(Long consultantId) {

        Optional<Consultant> consultantOptional = consultantRepository.findById(consultantId);

        if (consultantOptional.isPresent()) {
            Optional<User> userOptional = userRepository.findByEmail(consultantOptional.get().getEmail());
            if (userOptional.isPresent()) {
                consultantRepository.delete(consultantOptional.get());
                tokenRepository.deleteAllByUserId(userOptional.get().getId());
                userRepository.delete(userOptional.get());
            } else {
                throw new RecordNotFoundException("User does not exists");
            }
        } else {
            throw new RecordNotFoundException("Consultant does not exists");
        }

        return consultantOptional.get().toDto(modelMapper);
    }

    @Override
    public ConsultantDto getConsultantDetailById(Long consultantId) {
        Optional<Consultant> consultantOptional = consultantRepository.findById(consultantId);
        if (consultantOptional.isPresent()) {
            return consultantOptional.get().toDto(modelMapper);
        } else {
            throw new RecordNotFoundException("Consultant record not found");
        }
    }

    @Override
    public ConsultantDto getConsultantDetailByEmail(String email) {
        Optional<Consultant> consultantOptional = consultantRepository.findByEmail(email);
        if (consultantOptional.isPresent()) {
            return consultantOptional.get().toDto(modelMapper);
        } else {
            throw new RecordNotFoundException("Consultant record not found");
        }
    }

    @Override
    public List<ConsultantDto> getAllConsultantDetailList() {

        return consultantRepository.findAll()
                .stream()
                .map(consultant -> consultant.toDto(modelMapper))
                .toList();
    }

    @Override
    public List<ListItemDto> getAvailabilityTimeSlots(String date, String day, Long consultantId) throws JsonProcessingException {

        Optional<Availability> availabilityOptional = availabilityRepository
                .findByDayAndConsultantConsultantId(Days.valueOf(day), consultantId);
        List<ListItemDto> listItemDtoList = new ArrayList<>();
        if (availabilityOptional.isPresent()) {
            String timeSlots = availabilityOptional.get().getTimeSlots();

            if (Boolean.FALSE.equals(availabilityOptional.get().getIsWorkDay())) {
                throw new RecordNotFoundException("Not a your working day");
            }

            Map<String, AppointmentDetail> map = appointmentDetailRepository
                    .findAllByDateAndConsultant_ConsultantIdAndAppointmentStatus(date, consultantId, AppointmentStatus.SCHEDULED)
                    .stream()
                    .collect(Collectors.toMap(AppointmentDetail::getTime, Function.identity()));

            try {
                ArrayList<String> timeList = objectMapper.readValue(timeSlots, ArrayList.class);

                for (String time : timeList) {
                    listItemDtoList.add(
                            ListItemDto.builder()
                                    .label(time)
                                    .value(time)
                                    .isNotAvailable(map.containsKey(time))
                                    .build());
                }

            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw e;
            }
        }
        return listItemDtoList;
    }
}
