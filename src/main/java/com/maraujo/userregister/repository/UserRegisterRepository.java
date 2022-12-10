package com.maraujo.userregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegisterRepository extends JpaRepository<UserEntity, Long> {
}
