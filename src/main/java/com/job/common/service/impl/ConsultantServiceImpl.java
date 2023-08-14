package com.job.common.service.impl;

import com.job.common.dto.ConsultantDto;
import com.job.common.repository.ConsultantRepository;
import com.job.common.service.ConsultantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ConsultantServiceImpl implements ConsultantService {
    private final ModelMapper modelMapper;
    private final ConsultantRepository consultantRepository;


    @Override
    public ConsultantDto save(ConsultantDto consultantDto) {
        try {

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return null;
    }

    @Override
    public ConsultantDto update(ConsultantDto consultantDto) {
        return null;
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
        return null;
    }
}
