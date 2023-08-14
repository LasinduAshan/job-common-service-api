package com.job.common.service;


import com.job.common.dto.ConsultantDto;

import java.util.List;

public interface ConsultantService {
    ConsultantDto save(ConsultantDto consultantDto);
    ConsultantDto update(ConsultantDto consultantDto);
    ConsultantDto delete(Long consultantId);
    ConsultantDto getConsultantDetailById(Long consultantId);
    List<ConsultantDto> getAllConsultantDetailList();

}
