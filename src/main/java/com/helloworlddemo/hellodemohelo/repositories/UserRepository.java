package com.helloworlddemo.hellodemohelo.repositories;

import com.helloworlddemo.hellodemohelo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Email එක හරහා User කෙනෙක් හොයාගැනීම (Login සහ Register වලට)
    User findByEmail(String email);
}