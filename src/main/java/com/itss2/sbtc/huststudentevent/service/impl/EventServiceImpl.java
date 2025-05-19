package com.itss2.sbtc.huststudentevent.service.impl;

import com.itss2.sbtc.huststudentevent.domain.Event;
import com.itss2.sbtc.huststudentevent.dto.request.EventRequest;
import com.itss2.sbtc.huststudentevent.dto.request.RegisterRequest;
import com.itss2.sbtc.huststudentevent.dto.response.EventResponse;
import com.itss2.sbtc.huststudentevent.exception.BaseException;
import com.itss2.sbtc.huststudentevent.repository.ApplicationRepository;
import com.itss2.sbtc.huststudentevent.repository.EventRepository;
import com.itss2.sbtc.huststudentevent.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ApplicationRepository applicationRepository;

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
                .mssv(event.getMssv())
                .status(event.getStatus())
                .type(event.getType())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .build();
    }

    @Override
    @Transactional
    public void registerEvent(RegisterRequest request) {
        String eventId = request.getEventId();
        String mssvId = request.getMssvId();

        if (!this.eventRepository.existsById(eventId)) {
            throw new BaseException("Event with id " + eventId + " does not exist");
        }

        Event event = this.eventRepository.findById(eventId).orElse(null);
        List<String> newMssvIds = event.getMssv();
        if (newMssvIds == null) {
            newMssvIds = new ArrayList<>();
        }
        if (newMssvIds.contains(mssvId)) {
            throw new BaseException("Mssv id " + mssvId + " has already been registered");
        }
        newMssvIds.add(mssvId);
        event.setMssv(newMssvIds);
        this.eventRepository.save(event);

        this.applicationRepository.registerEvent(mssvId, eventId);
    }

    @Override
    public EventResponse getEventDetails(String eventId) {
        Event event = this.eventRepository.findById(eventId).orElseThrow(
                () -> new BaseException("Event with id " + eventId + " does not exist"));

        return EventResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .location(event.getLocation())
                .description(event.getDescription())
                .image(event.getImage())
                .status(event.getStatus())
                .mssv(event.getMssv())
                .type(event.getType())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .build();
    }

    @Override
    public Page<EventResponse> getEvents(Pageable pageable) {
        return this.eventRepository.findAll(pageable).map(
                event -> EventResponse.builder()
                        .id(event.getId())
                        .name(event.getName())
                        .startDate(event.getStartDate())
                        .endDate(event.getEndDate())
                        .location(event.getLocation())
                        .description(event.getDescription())
                        .image(event.getImage())
                        .status(event.getStatus())
                        .mssv(event.getMssv())
                        .type(event.getType())
                        .createdAt(event.getCreatedAt())
                        .updatedAt(event.getUpdatedAt())
                        .build()
        );
    }
}
