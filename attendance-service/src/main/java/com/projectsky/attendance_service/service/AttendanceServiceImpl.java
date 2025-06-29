package com.projectsky.attendance_service.service;

import com.projectsky.attendance_service.client.EventClient;
import com.projectsky.attendance_service.dto.AttendanceCreateDto;
import com.projectsky.attendance_service.dto.AttendanceDto;
import com.projectsky.attendance_service.model.Attendance;
import com.projectsky.attendance_service.repository.AttendanceRepository;
import com.projectsky.common.dto.EventDto;
import com.projectsky.common.exception.EventNotFoundException;
import com.projectsky.common.exception.ExternalServerException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final EventClient eventClient;
    private final AttendanceRepository repository;

    @Override
    public Long registerAttendance(AttendanceCreateDto dto) {
        try{
            eventClient.getEventById(dto.eventId());
            return repository.save(toEntity(dto)).getId();
        } catch (FeignException.NotFound ex){
            throw new EventNotFoundException("Event not found");
        } catch (FeignException ex){
            System.out.println(ex.getMessage());
            throw new ExternalServerException("Failed to fetch event from event-service. Status: " + ex.status());
        }
    }

    @Override
    public List<AttendanceDto> getAttendanceByEvent(Long eventId) {
        return repository.findByEventId(eventId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<AttendanceDto> getAttendanceByUser(Long userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private Attendance toEntity(AttendanceCreateDto dto) {
        return Attendance.builder()
                .eventId(dto.eventId())
                .userId(dto.userId())
                .registeredAt(LocalDateTime.now())
                .build();
    }

    private AttendanceDto toDto(Attendance attendance) {
        return AttendanceDto.builder()
                .id(attendance.getId())
                .eventId(attendance.getEventId())
                .userId(attendance.getUserId())
                .registeredAt(attendance.getRegisteredAt())
                .build();
    }
}
