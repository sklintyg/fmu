package se.inera.fmu.domain.model.eavrop.booking;


import se.inera.fmu.domain.model.eavrop.ArendeId;
import se.inera.fmu.domain.model.eavrop.EavropEvent;

public class BookingDeviationResponseEvent extends EavropEvent{
	
	//~ Constructors ===================================================================================================
	public BookingDeviationResponseEvent(final ArendeId arendeId) {
		super(arendeId);
	}

	//~ Property Methods ===============================================================================================

}