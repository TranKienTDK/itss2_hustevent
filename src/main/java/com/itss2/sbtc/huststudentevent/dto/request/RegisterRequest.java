package com.itss2.sbtc.huststudentevent.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String mssvId;
    private String eventId;
}
