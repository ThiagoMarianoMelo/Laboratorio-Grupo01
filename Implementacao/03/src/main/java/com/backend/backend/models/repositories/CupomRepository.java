package com.backend.backend.models.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.entities.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, UUID> {
    
}
