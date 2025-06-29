package com.projectsky.common.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EventDto(
        Long id,
        String name,
        String location,
        LocalDateTime dateTime
) {
}
