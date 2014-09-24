package se.inera.fmu.domain.model.landsting;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.ToString;

import org.apache.commons.lang3.Validate;

import se.inera.fmu.domain.model.hos.hsa.HsaBefattning;
import se.inera.fmu.domain.model.hos.hsa.HsaId;
import se.inera.fmu.domain.model.hos.personal.HoSPersonal;
import se.inera.fmu.domain.model.shared.Name;

@Entity
@ToString
public class Landstingssamordnare extends HoSPersonal {

	@ManyToOne
	private Landsting landsting; //TODO:should this mapping be in superclass

	Landstingssamordnare() {
		// Needed by hibernate
	}

	public Landstingssamordnare(final HsaId hsaId, final Name name, final HsaBefattning hsaBefattning, final Landsting landsting) {
		super(hsaId, name);
		Validate.notNull(landsting);
		this.setLandsting(landsting);
		this.setHsaBefattning(hsaBefattning);
	}
	
	private void setLandsting(Landsting landsting){
		this.landsting = landsting;
	}
	
	public Landsting getLandsting(){
		return this.landsting;
	}
}
