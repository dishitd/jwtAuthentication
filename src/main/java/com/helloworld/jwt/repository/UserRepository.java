package com.helloworld.jwt.repository;

import com.helloworld.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String userName);

    User save(User user);

    boolean existsByUsername(String userName);

    @Transactional
    void deleteByUsername(String username);

}

