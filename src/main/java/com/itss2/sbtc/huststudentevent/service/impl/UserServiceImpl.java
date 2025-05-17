package com.itss2.sbtc.huststudentevent.service.impl;

import com.itss2.sbtc.huststudentevent.domain.User;
import com.itss2.sbtc.huststudentevent.repository.UserRepository;
import com.itss2.sbtc.huststudentevent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }
}
