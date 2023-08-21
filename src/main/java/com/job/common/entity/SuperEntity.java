package com.job.common.entity;

import com.job.common.dto.SuperDto;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@MappedSuperclass
@Getter
@Setter
public abstract class SuperEntity<D extends SuperDto> {

    public abstract D toDto(ModelMapper modelMapper);

}
