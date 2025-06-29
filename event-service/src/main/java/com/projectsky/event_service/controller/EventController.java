package com.projectsky.event_service.controller;

import com.projectsky.event_service.dto.EventCreateDto;
import com.projectsky.common.dto.EventDto;
import com.projectsky.event_service.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents(){
        List<EventDto> allEvents = eventService.getAllEvents();
        return allEvents
                .isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(allEvents);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDto> getEventById(
            @PathVariable Long eventId)
    {
        return ResponseEntity.ok(eventService.getEventById(eventId));
    }

    @PostMapping
    public ResponseEntity<Long> createEvent(
            @RequestBody @Valid EventCreateDto eventDto
    ){
        return ResponseEntity.ok(eventService.createEvent(eventDto));
    }
}
