package com.itss2.sbtc.huststudentevent.dto.response;

import com.itss2.sbtc.huststudentevent.util.constant.EventTypeEnum;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventResponse {
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private String description;
    private String image;
    private String hostName;
    private String hostPhone;
    private String hostEmail;
    private Integer quantity;
    private Integer numberOfMssv;
    private EventTypeEnum type;
    private List<String> mssv;
    private boolean applicationStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
