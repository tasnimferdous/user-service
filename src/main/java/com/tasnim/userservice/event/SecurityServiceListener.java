package com.tasnim.userservice.event;

import com.tasnim.userservice.entity.UserInfo;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceListener {
    @KafkaListener(
            topics = "${spring.kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(UserInfo userInfo) {
        System.out.println(userInfo.getUsername());
    }
}
