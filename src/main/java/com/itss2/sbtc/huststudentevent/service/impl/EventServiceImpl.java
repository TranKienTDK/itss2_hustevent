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
                .hostName(eventRequest.getHostName())
                .hostPhone(eventRequest.getHostPhone())
                .hostEmail(eventRequest.getHostEmail())
                .quantity(eventRequest.getQuantity())
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
                .type(event.getType())
                .hostName(event.getHostName())
                .hostPhone(event.getHostPhone())
                .hostEmail(event.getHostEmail())
                .quantity(event.getQuantity())
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
            throw new BaseException("Student id " + mssvId + " has already been registered");
        }

        if (newMssvIds.size() == event.getQuantity()) {
            throw new BaseException("This event has reached its registration limit.");
        }

        newMssvIds.add(mssvId);
        event.setMssv(newMssvIds);
        this.eventRepository.save(event);

        this.applicationRepository.registerEvent(mssvId, eventId);
    }

    @Override
    public EventResponse getEventDetails(String eventId, String mssvId) {
        Event event = this.eventRepository.findById(eventId).orElseThrow(
                () -> new BaseException("Event with id " + eventId + " does not exist"));

        if (mssvId == null) {
            return EventResponse.builder()
                    .id(event.getId())
                    .name(event.getName())
                    .startDate(event.getStartDate())
                    .endDate(event.getEndDate())
                    .location(event.getLocation())
                    .description(event.getDescription())
                    .image(event.getImage())
                    .mssv(event.getMssv())
                    .type(event.getType())
                    .hostName(event.getHostName())
                    .hostPhone(event.getHostPhone())
                    .hostEmail(event.getHostEmail())
                    .quantity(event.getQuantity())
                    .numberOfMssv(event.getMssv() == null ? 0 : event.getMssv().size())
                    .createdAt(event.getCreatedAt())
                    .updatedAt(event.getUpdatedAt())
                    .applicationStatus(false)
                    .build();
        }

        List<String> mssvIds = event.getMssv();
        boolean isRegistered = mssvIds != null && mssvIds.contains(mssvId);

        return EventResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .location(event.getLocation())
                .description(event.getDescription())
                .image(event.getImage())
                .mssv(event.getMssv())
                .hostName(event.getHostName())
                .hostPhone(event.getHostPhone())
                .hostEmail(event.getHostEmail())
                .quantity(event.getQuantity())
                .numberOfMssv(event.getMssv() == null ? 0 : event.getMssv().size())
                .type(event.getType())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .applicationStatus(isRegistered)
                .build();
    }

    @Override
    public Page<EventResponse> getEvents(Pageable pageable, String mssvId) {
        Page<Event> eventPage = this.eventRepository.findAll(pageable);

        if (mssvId == null) {
            return eventPage.map(event -> EventResponse.builder()
                    .id(event.getId())
                    .name(event.getName())
                    .startDate(event.getStartDate())
                    .endDate(event.getEndDate())
                    .location(event.getLocation())
                    .description(event.getDescription())
                    .image(event.getImage())
                    .mssv(event.getMssv())
                    .hostName(event.getHostName())
                    .hostPhone(event.getHostPhone())
                    .hostEmail(event.getHostEmail())
                    .quantity(event.getQuantity())
                    .numberOfMssv(event.getMssv() == null ? 0 : event.getMssv().size())
                    .type(event.getType())
                    .createdAt(event.getCreatedAt())
                    .updatedAt(event.getUpdatedAt())
                    .applicationStatus(false)
                    .build());
        }

        return eventPage.map(event -> {
            EventResponse eventResponse = new EventResponse();
            List<String> mssvIds = event.getMssv();
            eventResponse.setApplicationStatus(mssvIds != null && mssvIds.contains(mssvId));

            eventResponse.setId(event.getId());
            eventResponse.setName(event.getName());
            eventResponse.setStartDate(event.getStartDate());
            eventResponse.setEndDate(event.getEndDate());
            eventResponse.setLocation(event.getLocation());
            eventResponse.setDescription(event.getDescription());
            eventResponse.setImage(event.getImage());
            eventResponse.setMssv(mssvIds);
            eventResponse.setType(event.getType());
            eventResponse.setHostName(event.getHostName());
            eventResponse.setHostPhone(event.getHostPhone());
            eventResponse.setHostEmail(event.getHostEmail());
            eventResponse.setQuantity(event.getQuantity());
            eventResponse.setNumberOfMssv(event.getMssv() == null ? 0 : event.getMssv().size());
            eventResponse.setCreatedAt(event.getCreatedAt());
            eventResponse.setUpdatedAt(event.getUpdatedAt());

            return eventResponse;
        });
    }
}
