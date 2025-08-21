package com.bci.infra.api.router.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDataDto {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<PhonesDto> phones;
    @JsonProperty("create_at")
    private Timestamp createAt;
    @JsonProperty("update_at")
    private Timestamp updateAt;
    @JsonProperty("last_login")
    private Timestamp lastLogin;
    private String token;
    private boolean active;
}
