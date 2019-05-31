package com.tpmdi.jpa.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/etudiants")
public class ControllerEtudiant {
    
    @Autowired
    private EtudiantRepository etudiants;
    
    @Autowired
    private EnseignantRepository enseignants;
    
    @PostMapping() 
    public Etudiants createEtudiant(@Valid @RequestBody Etudiants etudiant){
        return this.etudiants.save(etudiant);
    }
    
   /* @GetMapping("/{id}") 
    public Etudiants getEtudiant(@PathVariable Long id){
        return this.etudiants.findById(id).orElseThrow(() -> 
                new ResourceNotFound("Etudiants", id)
        );
    }*/
    @GetMapping("/{id}") 
    public ResponseEntity<Etudiants> getEtudiant(@PathVariable Long id) {
        Optional<Etudiants> etudiant = etudiants.findById(id);
        if (!etudiant.isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(etudiant.get());
    }
    
    @GetMapping()
    public ResponseEntity<List<Etudiants>> getEtudiants() {
        return ResponseEntity.ok(etudiants.findAll());
    }
    
    /*@PutMapping()
    public Etudiants updateEtudiant(@PathVariable Long id, @Valid @RequestBody Etudiants etudiant){
        return this.etudiants.findById(id).map((toUpdate) -> {
            toUpdate.setFirstName(etudiant.getFirstName());
            toUpdate.setLastName(etudiant.getLastName());
            toUpdate.setFiliere(etudiant.getFiliere());
            return this.etudiants.save(toUpdate);
        }).orElseThrow(() -> new ResourceNotFound("Etudiants", etudiant.getId()));
    }*/
    
    @PutMapping("/{id}")
    public ResponseEntity<Etudiants> updateEtudiant(@PathVariable Long id, @Valid @RequestBody Etudiants etudiant) {
        if (!etudiants.findById(id).isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(etudiants.save(etudiant));
    }
    
    /*@DeleteMapping("/{id}") 
    public ResponseEntity deleteEtudiant(@PathVariable Long id){
        return this.etudiants.findById(id).map((toDelete) -> {
            this.etudiants.delete(toDelete);
            return ResponseEntity.ok("Etudiant id " + id + " deleted");
        }).orElseThrow(() -> new ResourceNotFound("Etudiants", id));
    }*/
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEtudiant(@PathVariable Long id) {
        if (!etudiants.findById(id).isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

       etudiants.deleteById(id);

        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}/enseignants")
    public Set<Enseignants> getEnseignants(@PathVariable Long id){
        return this.etudiants.findById(id).map((etudiant) -> {
            return etudiant.getEnseignants();
        }).orElseThrow(() -> new ResourceNotFound("Etudiants", id));
    }
}