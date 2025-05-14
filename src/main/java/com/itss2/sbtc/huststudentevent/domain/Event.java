package com.itss2.sbtc.huststudentevent.domain;

import com.itss2.sbtc.huststudentevent.util.constant.EventStatusEnum;
import com.itss2.sbtc.huststudentevent.util.constant.EventTypeEnum;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "events")
public class Event extends BaseEntity {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String location;
    private String image;
    private EventStatusEnum status;
    private EventTypeEnum type;
}
