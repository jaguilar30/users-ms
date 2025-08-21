package com.bci.domain.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Builder
public class User {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<Phones> phones;
    private Timestamp createAt;
    private Timestamp updateAt;
    private Timestamp lastLogin;
    private String token;
    private boolean active;
}
