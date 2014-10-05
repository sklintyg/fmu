package se.inera.fmu.domain.model.eavrop.assignment;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.ToString;

import org.apache.commons.lang.Validate;

import se.inera.fmu.domain.model.hos.vardgivare.Vardgivarenhet;
import se.inera.fmu.domain.shared.AbstractBaseEntity;
import se.inera.fmu.domain.shared.IEntity;

@Entity
@Table(name = "T_ASSIGNMENT")
@ToString
public class EavropAssignment extends AbstractBaseEntity implements
		IEntity<EavropAssignment> {

	private static final long serialVersionUID = 1L;

	// database primary key, using UUID and not a Hibernate sequence
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	private String id;

	// TODO:
	@OneToOne 
	private Vardgivarenhet vardgivarenhet;
	
    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private EavropAssignmentStatusType assignmentStatus;

	// ~ Constructors ===================================================================================================

	EavropAssignment() {
		// Needed by Hibernate
	}

	public EavropAssignment(Vardgivarenhet vardivareenhet) {
		this.setId(UUID.randomUUID().toString());
		setAssignmentStatus(this.assignmentStatus = EavropAssignmentStatusType.ASSIGNED);
		Validate.notNull(vardivareenhet);
		setVardgivarenhet(vardivareenhet);
	}

	// ~ Property Methods ===============================================================================================

	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	private void setVardgivarenhet(Vardgivarenhet vardgivarenhet) {
		this.vardgivarenhet = vardgivarenhet;
	}

	public Vardgivarenhet getVardgivarenhet() {
		return this.vardgivarenhet;
	}
	
	private EavropAssignmentStatusType getAssignmentStatus() {
		return assignmentStatus;
	}

	private void setAssignmentStatus(EavropAssignmentStatusType assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}
	

	//TODO: Are these status transitions covered by LastModifiedBy and LastModfiedDate or should we assingn it explicitly, could last LastModfiedDate be changed bay system when changes are made to owner 
	public void acceptAssignment(){
		if( EavropAssignmentStatusType.ASSIGNED.equals(this.getAssignmentStatus())){
			this.setAssignmentStatus(EavropAssignmentStatusType.ACCEPTED);
		}else{
			//TODO: throw something
		}
	}

	public void rejectAssignment(){
		if( EavropAssignmentStatusType.ASSIGNED.equals(this.getAssignmentStatus())){
			this.setAssignmentStatus(EavropAssignmentStatusType.REJECTED);
		}else{
			//TODO: throw something
		}
	}

	// ~ Other Methods ==================================================================================================

	@Override
	public boolean sameIdentityAs(EavropAssignment other) {
		return other != null && this.id.equals(other.id);
	}

	/**
	 * @param object to compare
	 * @return True if they have the same identity
	 * @see #sameIdentityAs(EavropAssignment)
	 */
	@Override
	public boolean equals(final Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;

		final EavropAssignment other = (EavropAssignment) object;
		return sameIdentityAs(other);
	}

	/**
	 * @return Hash code of tracking id.
	 */
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}