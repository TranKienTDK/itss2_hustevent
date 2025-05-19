package com.itss2.sbtc.huststudentevent.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "applications")
public class Application extends BaseEntity {
    private String mssv;
    private String eventId;
    private LocalDateTime appliedAt;
}
