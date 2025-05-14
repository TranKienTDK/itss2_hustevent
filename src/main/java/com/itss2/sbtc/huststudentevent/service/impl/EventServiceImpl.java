package com.itss2.sbtc.huststudentevent.service.impl;

import com.itss2.sbtc.huststudentevent.domain.Event;
import com.itss2.sbtc.huststudentevent.dto.request.EventRequest;
import com.itss2.sbtc.huststudentevent.dto.response.EventResponse;
import com.itss2.sbtc.huststudentevent.repository.EventRepository;
import com.itss2.sbtc.huststudentevent.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public EventResponse createEvent(EventRequest eventRequest) {
        Event event = Event.builder()
                .name(eventRequest.getName())
                .description(eventRequest.getDescription())
                .startDate(eventRequest.getStartDate())
                .endDate(eventRequest.getEndDate())
                .location(eventRequest.getLocation())
                .image(eventRequest.getImage())
                .status(eventRequest.getStatus())
                .type(eventRequest.getType())
                .build();

        event = this.eventRepository.save(event);
        return EventResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .location(event.getLocation())
                .description(event.getDescription())
                .image(event.getImage())
                .status(event.getStatus())
                .type(event.getType())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .build();
    }
}
