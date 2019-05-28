package com.tpmdi.jpa.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpmdi.jpa.exceptions.ResourceNotFound;
import com.tpmdi.jpa.models.Enseignants;
import com.tpmdi.jpa.models.Etudiants;
import com.tpmdi.jpa.repositories.EnseignantRepository;
import com.tpmdi.jpa.repositories.EtudiantRepository;

@RestController
@RequestMapping("api/v1/enseignants") 
public class ControllerEnseignant {
    
    @Autowired 
    private EnseignantRepository enseignants;
    
    @Autowired
    private EtudiantRepository etudiants;
    
    @PostMapping() 
    public Enseignants createEnseignant(@Valid @RequestBody Enseignants enseignant){
        return this.enseignants.save(enseignant);
    }
    
    @GetMapping() 
    public Page<Enseignants> getEnseignants(Pageable pageable){
        return this.enseignants.findAll(pageable);
    }
    
    @GetMapping("/{id}") 
    public Enseignants getEnseignant(@PathVariable Long id){
        return this.enseignants.findById(id).orElseThrow(
                () -> new ResourceNotFound("Enseignants", id)
        );
    }
    
    @PutMapping()
    public Enseignants updateEnseignant(@Valid @RequestBody Enseignants enseignant){
        return this.enseignants.findById(enseignant.getId()).map((toUpdate) -> {
            toUpdate.setFirstName(enseignant.getFirstName());
            toUpdate.setLastName(enseignant.getLastName());
            toUpdate.setDepartement(enseignant.getDepartement());
            return this.enseignants.save(toUpdate);
        }).orElseThrow(() -> new ResourceNotFound("Enseignants", enseignant.getId()));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEnseignant(@PathVariable Long id){
        return this.enseignants.findById(id).map((toDelete) -> {
            this.enseignants.delete(toDelete);
            return ResponseEntity.ok("Enseignant id " + id + " deleted");
        }).orElseThrow(() -> new ResourceNotFound("Enseignants", id));
    }
    
    @GetMapping("/{enseignantId}/etudiants")
    public Set<Etudiants> getEtudiants(@PathVariable Long enseignantId){
        return this.enseignants.findById(enseignantId).map((enseignant) -> {
            return enseignant.getEtudiants();
        }).orElseThrow(() -> new ResourceNotFound("Enseignants", enseignantId));
    }
    
    @PostMapping("/{id}/etudiants/{etudiantId}")
    public Set<Etudiants> addStudent(@PathVariable Long id, @PathVariable Long etudiantId){
        Etudiants etudiant = this.etudiants.findById(etudiantId).orElseThrow(
                () -> new ResourceNotFound("Etudiants", etudiantId)
        );
        return this.enseignants.findById(id).map((enseignant) -> {
            enseignant.getEtudiants().add(etudiant);
            return this.enseignants.save(enseignant).getEtudiants(); 
        }).orElseThrow(() -> new ResourceNotFound("Enseignants", id));
    }
    
}