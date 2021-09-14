package com.user.service.service.impl;

import com.user.entity.User;
import com.user.repository.UserRepository;
import com.user.service.inf.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements IUserService {

    private static final String MESSAGE_QUEUE = "message_queue";

    @Autowired
    UserRepository userRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public User createUser(User user) {
        jmsTemplate.convertAndSend(MESSAGE_QUEUE, user);
        log.info("sending request: "+ user);
        return user;
    }

    @JmsListener(destination = MESSAGE_QUEUE)
    public void receiveMessage(User user){
        log.info("Request Received " + user);
        userRepository.save(user);
    }
}
