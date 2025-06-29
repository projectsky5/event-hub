package com.projectsky.event_service.service;

import com.projectsky.event_service.dto.EventCreateDto;
import com.projectsky.event_service.dto.EventDto;

import java.util.List;

public interface EventService {

    Long createEvent(EventCreateDto eventDto);
    List<EventDto> getAllEvents();
    EventDto getEventById(Long id);
}
