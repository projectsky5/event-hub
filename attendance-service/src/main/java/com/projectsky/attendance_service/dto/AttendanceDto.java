package com.projectsky.attendance_service.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AttendanceDto(
        Long id,
        Long eventId,
        Long userId,
        LocalDateTime registeredAt
) {
}
