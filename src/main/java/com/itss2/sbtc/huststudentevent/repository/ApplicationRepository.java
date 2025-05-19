package com.itss2.sbtc.huststudentevent.repository;

import com.itss2.sbtc.huststudentevent.domain.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends MongoRepository<Application, String>, ApplicationRepositoryCustom {
}
