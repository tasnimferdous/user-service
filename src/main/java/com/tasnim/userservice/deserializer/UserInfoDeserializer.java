package com.tasnim.userservice.deserializer;

import com.tasnim.userservice.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import tools.jackson.databind.ObjectMapper;

import java.nio.ByteBuffer;
import java.util.Map;

@Slf4j
public class UserInfoDeserializer implements Deserializer<UserInfo> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public UserInfo deserialize(String s, byte[] bytes) {
        try {
            return new ObjectMapper().readValue(bytes, UserInfo.class);
        } catch (Exception e) {
            log.error("Exception: ",e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public UserInfo deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public UserInfo deserialize(String topic, Headers headers, ByteBuffer data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
