package com.bci.infra.adapter.db.jpa;

import com.bci.infra.adapter.db.entites.PhonesDto;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface PhonesJpaRepository extends CrudRepository<PhonesDto, UUID> {
}
