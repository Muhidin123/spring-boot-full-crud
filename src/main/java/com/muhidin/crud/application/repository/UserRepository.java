package com.muhidin.crud.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.muhidin.crud.application.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
