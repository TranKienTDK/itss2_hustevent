package com.itss2.sbtc.huststudentevent.dto.request;

import com.itss2.sbtc.huststudentevent.util.constant.EventStatusEnum;
import com.itss2.sbtc.huststudentevent.util.constant.EventTypeEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequest {
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private String description;
    private String image;
    private EventStatusEnum status;
    private EventTypeEnum type;
}
