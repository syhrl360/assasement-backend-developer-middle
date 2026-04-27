package com.practice.authority.repository;

import com.practice.authority.entity.UserAuthority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthorityRepository extends CrudRepository<UserAuthority, Integer> {

    Optional<UserAuthority> findByUsername(String username);
}
