package com.itss2.sbtc.huststudentevent.controller;

import com.itss2.sbtc.huststudentevent.dto.request.EventRequest;
import com.itss2.sbtc.huststudentevent.dto.request.RegisterRequest;
import com.itss2.sbtc.huststudentevent.dto.response.ApiResponse;
import com.itss2.sbtc.huststudentevent.dto.response.EventResponse;
import com.itss2.sbtc.huststudentevent.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{eventId}")
    public ApiResponse<EventResponse> getEvent(@PathVariable String eventId) {
        return ApiResponse.<EventResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Lấy thông tin chi tiết sự kiện thành công")
                .data(this.eventService.getEventDetails(eventId))
                .build();
    }

    @GetMapping
    public ApiResponse<Page<EventResponse>> getAllEvents(
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ApiResponse.<Page<EventResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Lấy danh sách sự kiện thành công")
                .data(this.eventService.getEvents(pageable))
                .build();
    }
}
