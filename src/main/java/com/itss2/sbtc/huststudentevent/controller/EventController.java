package com.itss2.sbtc.huststudentevent.controller;

import com.itss2.sbtc.huststudentevent.dto.request.EventRequest;
import com.itss2.sbtc.huststudentevent.dto.request.RegisterRequest;
import com.itss2.sbtc.huststudentevent.dto.response.ApiResponse;
import com.itss2.sbtc.huststudentevent.dto.response.EventResponse;
import com.itss2.sbtc.huststudentevent.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ApiResponse<EventResponse> createEvent(@RequestBody EventRequest eventRequest) {
        return ApiResponse.<EventResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Tạo sự kiện thành công")
                .data(this.eventService.createEvent(eventRequest))
                .build();
    }

    @PostMapping("/application")
    public ApiResponse<Void> registerEvent(@RequestBody RegisterRequest request) {
        this.eventService.registerEvent(request);
        return ApiResponse.<Void>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Đăng ký tham gia sự kiện thành công")
                .data(null)
                .build();
    }
}
