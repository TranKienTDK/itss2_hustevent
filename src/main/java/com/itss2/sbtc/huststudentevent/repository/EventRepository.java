package com.itss2.sbtc.huststudentevent.repository;

import com.itss2.sbtc.huststudentevent.domain.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event, String>, CustomEventRepository {
}
