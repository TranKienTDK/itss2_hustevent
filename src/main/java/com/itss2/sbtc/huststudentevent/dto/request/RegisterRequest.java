package com.itss2.sbtc.huststudentevent.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String userId;
    private String eventId;
}
