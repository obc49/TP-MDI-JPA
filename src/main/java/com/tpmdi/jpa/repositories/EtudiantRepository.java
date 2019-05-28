package com.tpmdi.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tpmdi.jpa.models.Etudiants;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiants, Long>{
    
}