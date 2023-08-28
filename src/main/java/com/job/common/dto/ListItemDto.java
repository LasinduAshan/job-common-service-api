package com.job.common.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ListItemDto {
    private Object value;
    private String label;
    private Boolean isNotAvailable;

}
