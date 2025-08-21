package com.bci.infra.adapter.db;

import com.bci.domain.port.out.UsersPortRepository;
import com.bci.infra.adapter.db.entites.UsersDto;
import com.bci.infra.adapter.db.jpa.PhonesJpaRepository;
import com.bci.infra.adapter.db.jpa.UsersJpaRepository;
import com.bci.infra.adapter.db.mapper.MapperUsersEntity;
import com.bci.domain.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository implements UsersPortRepository {

    @Autowired
    private UsersJpaRepository usersJpaRepository;

    @Autowired
    private PhonesJpaRepository phonesJpaRepository;

    @Transactional
    public User save(UsersDto userToSave){
        return MapperUsersEntity.toUser(usersJpaRepository.save(userToSave));
    }

    public User getUserByEmail(String email){
        return MapperUsersEntity.toUser(usersJpaRepository.findByEmail(email));
    }
}
