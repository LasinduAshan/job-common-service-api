package com.job.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.job.common.entity.SuperEntity;
import com.job.common.enums.RecordStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public abstract class SuperDto<E extends SuperEntity> implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private LocalDateTime createdDate;
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private LocalDateTime modifiedDate;
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private RecordStatus recordStatus = RecordStatus.ACTIVE;

    public abstract E toEntity(ModelMapper modelMapper);
}
