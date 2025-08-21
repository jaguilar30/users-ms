package com.bci.infra.adapter.db.entites;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints={@UniqueConstraint(columnNames = {"email"})})
public class UsersDto {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 500)
    private String password;

    @Column(name = "create_at")
    private Timestamp createAt;

    @Column(name = "update_at")
    private Timestamp updateAt;

    @Column(name = "last_login")
    private Timestamp lastLogin;

    @Column(name = "token", length= 2000)
    private String token;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy= "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhonesDto> phones;
}
