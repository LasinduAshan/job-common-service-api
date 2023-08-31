package com.job.common.entity;

import com.job.common.dto.SuperDto;
import com.job.common.enums.RecordStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class SuperEntity<D extends SuperDto> {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus = RecordStatus.ACTIVE;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @UpdateTimestamp
    @Column
    private LocalDateTime modifiedDate;

    public abstract D toDto(ModelMapper modelMapper);

}
