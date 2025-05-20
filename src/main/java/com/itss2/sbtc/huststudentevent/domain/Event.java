package com.itss2.sbtc.huststudentevent.domain;

import com.itss2.sbtc.huststudentevent.util.constant.EventTypeEnum;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

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
    private String hostName;
    private String hostPhone;
    private String hostEmail;
    private Integer quantity;
    private EventTypeEnum type;
    List<String> mssv;
}
