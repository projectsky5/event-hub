package com.projectsky.attendance_service.service;

import com.projectsky.attendance_service.dto.AttendanceCreateDto;
import com.projectsky.attendance_service.dto.AttendanceDto;

import java.util.List;

public interface AttendanceService {

    Long registerAttendance(AttendanceCreateDto dto);
    List<AttendanceDto> getAttendanceByEvent(Long eventId);
    List<AttendanceDto> getAttendanceByUser(Long userId);
}
