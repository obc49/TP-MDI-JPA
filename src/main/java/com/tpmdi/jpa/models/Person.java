package com.tpmdi.jpa.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class Person extends ModelAudit{
    
    @Column(nullable = false)
    @Size(min = 2, max = 100)
    private String firstName;
    
    @Column(nullable = false)
    @Size(min = 2, max = 100)
    private String lastName;
    
    public Person() { 
    	
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
    	return firstName;
    }
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }

    public String getLastName() {
    	return lastName;
    }
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    
}
