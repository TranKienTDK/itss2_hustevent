package com.itss2.sbtc.huststudentevent.repository;

import com.itss2.sbtc.huststudentevent.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
