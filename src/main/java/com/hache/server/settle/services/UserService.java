package com.hache.server.settle.services;

import com.hache.server.settle.application.dto.UserDto;
import com.hache.server.settle.persistences.ISettlePersist;
import com.hache.server.settle.persistences.postgres.entity.EnumRol;
import com.hache.server.settle.security.authentication.JWTUtil;
import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Propagation;
// import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class UserService {

    //@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Mono<UserDto> findUser(final String email, final ISettlePersist persist) {
        return persist.findUser(email);
    }

    public Mono<UserDto> findUserAndMeets(final String email, final ISettlePersist persist) {
        return persist.findUserAndMeets(email);
    }

    public Mono<UserDto> createUser(final String email, String name, final String password, final Boolean guest, final ISettlePersist persist) {
        ArrayList roles = new ArrayList();
        roles.add(EnumRol.USER.name());
        return persist.createUser(email, name, JWTUtil.passwordEncoder().encode(password), guest, roles);
    }
}
