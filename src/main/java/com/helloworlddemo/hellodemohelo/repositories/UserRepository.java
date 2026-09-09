package com.helloworlddemo.hellodemohelo.repositories;

import com.helloworlddemo.hellodemohelo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}