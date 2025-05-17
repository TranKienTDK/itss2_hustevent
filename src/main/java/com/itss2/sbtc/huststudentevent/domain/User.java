package com.itss2.sbtc.huststudentevent.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User extends BaseEntity {
    private String username;
    private String email;
    private String phone;
}
