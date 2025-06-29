package com.projectsky.attendance_service.controller;

import com.projectsky.attendance_service.dto.AttendanceCreateDto;
import com.projectsky.attendance_service.dto.AttendanceDto;
import com.projectsky.attendance_service.service.AttendanceService;
import com.projectsky.common.dto.EventDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService service;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AttendanceDto>> getAttendanceByUserId(
            @PathVariable Long userId
    ) {
        List<AttendanceDto> attendanceByUser = service.getAttendanceByUser(userId);
        return attendanceByUser.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(attendanceByUser);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<AttendanceDto>> getAttendanceByEventId(
            @PathVariable Long eventId
    ) {
        List<AttendanceDto> attendanceByEvent = service.getAttendanceByEvent(eventId);
        return attendanceByEvent.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(attendanceByEvent);
    }

    @PostMapping
    public ResponseEntity<Long> createAttendance(
            @RequestBody @Valid AttendanceCreateDto dto
    ) {
        return ResponseEntity.ok(service.registerAttendance(dto));
    }
}
