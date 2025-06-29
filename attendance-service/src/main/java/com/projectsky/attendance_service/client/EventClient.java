package com.projectsky.attendance_service.client;

import com.projectsky.common.dto.EventDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "event-service")
public interface EventClient {

    @GetMapping("/api/event/{eventId}")
    EventDto getEventById(
            @PathVariable Long eventId
    );
}
