package com.bci.infra.adapter.db.jpa;

import com.bci.infra.adapter.db.entites.UsersDto;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface UsersJpaRepository extends CrudRepository<UsersDto, UUID> {
     UsersDto findByEmail(String firstName);
}
