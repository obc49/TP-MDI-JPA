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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Enseignants extends Person {
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(nullable = false)
	    @Size(min = 2, max = 100)
	    private  String departement;
	    
	    @ManyToMany(
	            fetch = FetchType.LAZY,
	            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
	    )
	    @JoinTable(
	            name = "enseignant_etudiant",
	            joinColumns = {@JoinColumn(name = "enseignant_id")},
	            inverseJoinColumns = {@JoinColumn(name = "etudiant_id")}
	    )
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @JsonIgnore
	    private Set<Etudiants> etudiants = new HashSet<>();
	    
	    //constructeurs
	    
	    public Enseignants() { }

	    public Enseignants(String firstName, String lastName, String departement) {
	        super(firstName, lastName);
	        this.departement = departement;
	    }
	    
	    //Getters and setters
	    
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDepartement() {
			return departement;
		}

		public void setDepartement(String departement) {
			this.departement = departement;
		}

		public Set<Etudiants> getEtudiants() {
			return etudiants;
		}
		
		public void setEtudiants(Set<Etudiants> etudiants) {
			this.etudiants = etudiants;
		}

}
