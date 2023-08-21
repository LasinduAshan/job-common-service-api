package com.job.common.dto;

import com.job.common.entity.SuperEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public abstract class SuperDto<E extends SuperEntity> implements Serializable {

    public abstract E toEntity(ModelMapper modelMapper);
}
