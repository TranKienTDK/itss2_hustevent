package com.itss2.sbtc.huststudentevent.repository.impl;

import com.itss2.sbtc.huststudentevent.domain.Application;
import com.itss2.sbtc.huststudentevent.repository.ApplicationRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class ApplicationRepositoryCustomImpl implements ApplicationRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public void registerEvent(String userId, String eventId) {
        this.mongoTemplate.save(
                Application.builder()
                        .userId(userId)
                        .eventId(eventId)
                        .appliedAt(LocalDateTime.now())
                        .build()
        );
    }
}
