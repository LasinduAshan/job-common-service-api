package com.job.common.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.job.common.dto.AvailabilityDto;
import com.job.common.dto.ConsultantDto;
import com.job.common.dto.ListItemDto;

import java.util.List;

public interface ConsultantService {
    ConsultantDto save(ConsultantDto consultantDto) throws Exception;
    ConsultantDto update(ConsultantDto consultantDto) throws Exception;
    ConsultantDto delete(Long consultantId);
    ConsultantDto getConsultantDetailById(Long consultantId);
    List<ConsultantDto> getAllConsultantDetailList();
    List<ListItemDto> getAvailabilityTimeSlots(String date, String day, Long consultantId) throws JsonProcessingException;

}
