package com.projectsky.event_service.service;

import com.projectsky.event_service.dto.EventCreateDto;
import com.projectsky.common.dto.EventDto;
import com.projectsky.common.exception.EventNotFoundException;
import com.projectsky.event_service.model.Event;
import com.projectsky.event_service.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Long createEvent(EventCreateDto dto) {
        Event event = Event.builder()
                .name(dto.name())
                .location(dto.location())
                .dateTime(dto.dateTime())
                .build();

        return eventRepository.save(event).getId();
    }

    @Override
    public List<EventDto> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public EventDto getEventById(Long id) {
        return eventRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));
    }

    private EventDto toDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .location(event.getLocation())
                .dateTime(event.getDateTime())
                .build();
    }
}
