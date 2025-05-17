package com.itss2.sbtc.huststudentevent.service;

import com.itss2.sbtc.huststudentevent.dto.request.EventRequest;
import com.itss2.sbtc.huststudentevent.dto.request.RegisterRequest;
import com.itss2.sbtc.huststudentevent.dto.response.EventResponse;

public interface EventService {
    EventResponse createEvent(EventRequest eventRequest);
    void registerEvent(RegisterRequest request);
}
