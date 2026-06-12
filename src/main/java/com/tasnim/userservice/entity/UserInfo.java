package com.tasnim.userservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Id
    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String profilePicture;
}
