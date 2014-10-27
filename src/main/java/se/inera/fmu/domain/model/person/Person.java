package se.inera.fmu.domain.model.person;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
	
@Entity
@Table(name = "T_PERSON")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Person implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PERSON_ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ROLE", nullable = true)
    private String role;
    
    //TODO: maybe also unit information?
    
    @Column(name = "ORGANISATION", nullable = true)
    private String organisation;

    
    //~ Constructors ===================================================================================================

	public Person() {
		//Needed by Hibernate
	}

	public Person(String name, String role, String organisation) {
		super();
		this.name = name;
		this.role = role;
		this.organisation = organisation;
	}


	//~ Property Methods ===============================================================================================

	public String getName() {
		return this.name;
	}

	private void setNamn(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	private void setRole(String role) {
		this.role = role;
	}

	public String getOrganisation() {
		return organisation;
	}

	private void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
}