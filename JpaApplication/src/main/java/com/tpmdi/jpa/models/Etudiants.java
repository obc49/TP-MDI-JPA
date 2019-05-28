package com.tpmdi.jpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Etudiants extends Person{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String filiere;
    
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}, 
            mappedBy = "etudiants"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Enseignants> Enseignants = new HashSet<>();
    
    // Constructeurs

    public Etudiants() {
    	
    }

    public Etudiants(String firstName, String lastName, String filiere) {
        super(firstName, lastName);
        this.filiere = filiere;
    }
    
    // Getters and setters

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	public Set<Enseignants> getEnseignants() {
		return Enseignants;
	}

	public void setEnseignants(Set<Enseignants> enseignants) {
		Enseignants = enseignants;
	}
    
   
}