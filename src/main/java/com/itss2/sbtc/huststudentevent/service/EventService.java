package com.itss2.sbtc.huststudentevent.service;

import com.itss2.sbtc.huststudentevent.dto.request.EventRequest;
import com.itss2.sbtc.huststudentevent.dto.request.RegisterRequest;
import com.itss2.sbtc.huststudentevent.dto.response.EventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
    EventResponse createEvent(EventRequest eventRequest);
    void registerEvent(RegisterRequest request);
    EventResponse getEventDetails(String eventId);
    Page<EventResponse> getEvents(Pageable pageable);
}
