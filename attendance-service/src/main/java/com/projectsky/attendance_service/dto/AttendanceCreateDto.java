package com.projectsky.attendance_service.dto;

import jakarta.validation.constraints.NotNull;

public record AttendanceCreateDto(
        @NotNull Long eventId,
        @NotNull Long userId
) {
}
