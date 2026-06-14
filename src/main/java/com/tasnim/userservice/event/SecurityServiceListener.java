package com.tasnim.userservice.event;

import com.tasnim.userservice.entity.UserInfo;
import com.tasnim.userservice.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecurityServiceListener {
    private final UserInfoRepository userInfoRepository;

    public SecurityServiceListener(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(UserInfo userInfo) {
        userInfoRepository.findByUsername((userInfo.getUsername()))
                .ifPresentOrElse(user -> {
                    log.info("User already exists: {}", user.getUsername());
                }, () -> {
                    userInfoRepository.save(userInfo);
                    log.info("User saved successfully: {}", userInfo.getUsername());
                });
    }
}
