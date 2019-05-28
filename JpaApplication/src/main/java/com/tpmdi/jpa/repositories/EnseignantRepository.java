package com.tpmdi.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tpmdi.jpa.models.Enseignants;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignants, Long>{
    
}
