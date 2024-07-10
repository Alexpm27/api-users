package com.example.securitywithjwt.persistence.repositories;

import com.example.securitywithjwt.persistence.models.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInterestRepository extends JpaRepository<Interest, Long> {
}
