package se.inera.fmu.domain.model.eavrop.booking.interpreter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import se.inera.fmu.domain.model.eavrop.InterpreterBookingEventDTO;
import se.inera.fmu.domain.model.eavrop.note.Note;
/**
 * This Class represents a booked interpreter resource.
 * It belongs to a booking and 'inherits' the bookings timespan but has its own status, and therefore also its own deviation notes
 */
@Embeddable
public class InterpreterBooking {
    
	@Column(name = "INTERPRETER_STATUS_TYPE", nullable = true, updatable = true)
    @Enumerated(EnumType.STRING)
    @NotNull
	private InterpreterBookingStatusType interpreterBookingStatus;
    
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="INTERPRETER_DEVIATION_NOTE_ID", nullable = true, updatable = true)
	private Note deviationNote;

	public InterpreterBooking() {
		this.interpreterBookingStatus = InterpreterBookingStatusType.INTERPRETER_BOOKED;
	}

	public InterpreterBookingStatusType getInterpreterBookingStatus() {
		return interpreterBookingStatus;
	}

	public void setInterpreterBookingStatus(
			InterpreterBookingStatusType interpreterBookingStatus) {
		this.interpreterBookingStatus = interpreterBookingStatus;
	}

	public Note getDeviationNote() {
		return deviationNote;
	}

	public void setDeviationNote(Note deviationNote) {
		this.deviationNote = deviationNote;
	}
	
	public boolean hasDeviation(){
		return InterpreterBookingStatusType.INTERPRETER_NOT_PRESENT.equals(this.getInterpreterBookingStatus());
	}

	public InterpreterBookingEventDTO getAsInterpreterBookingEventDTO() {
		String comment = (this.deviationNote!=null)?this.deviationNote.getText():null;
		InterpreterBookingEventDTO interpreterBookingEventDTO = new InterpreterBookingEventDTO(getInterpreterBookingStatus(), comment);
		return interpreterBookingEventDTO;
	}	


}
