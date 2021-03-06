package se.inera.fmu.interfaces.managing.rest.dto;

import java.util.ArrayList;
import java.util.List;

import se.inera.fmu.domain.model.eavrop.booking.BookingStatusType;
import se.inera.fmu.domain.model.eavrop.booking.interpreter.InterpreterBookingStatusType;

@SuppressWarnings("rawtypes")
public class StatusDTO {
	private Status currentStatus;
	private List<Status> statuses;
	private String comment;

	public String getComment() {
		return comment;
	}

	public Status getCurrentStatus() {
		return currentStatus;
	}

	public List<Status> getStatuses() {
		return statuses;
	}

	public StatusDTO setComment(String comment) {
		this.comment = comment;
		return this;
	}

	public StatusDTO setCurrentStatus(Enum currentStatus) {
		Status status = new Status();
		if (currentStatus instanceof BookingStatusType) {
			status.setName(currentStatus).setRequireComment(
					((BookingStatusType) currentStatus).isDeviant());
			this.currentStatus = status;
		} else if (currentStatus instanceof InterpreterBookingStatusType) {
			status.setName(currentStatus).setRequireComment(
					((InterpreterBookingStatusType) currentStatus).isDeviant());
			this.currentStatus = status;
		}

		return this;
	}

	private boolean isValidHandelseStatus(Object status) {
		return status instanceof BookingStatusType
				|| status instanceof InterpreterBookingStatusType;
	}

	public StatusDTO setStatuses(Object[] objects) {
		List<Status> retval = new ArrayList<StatusDTO.Status>();
		for (Object status : objects) {
			if (isValidHandelseStatus(status)) {
				Status s = new Status();
				if (status instanceof BookingStatusType) {
					BookingStatusType bt = (BookingStatusType) status;
					s.setName(bt).setRequireComment(bt.isDeviant());
				} else {
					InterpreterBookingStatusType it = (InterpreterBookingStatusType) status;
					s.setName(it).setRequireComment(it.isDeviant());
				}
				retval.add(s);
			}
		}

		this.statuses = retval;
		return this;
	}

	protected class Status {
		private Enum name;
		private Boolean requireComment;

		public Enum getName() {
			return name;
		}

		public Boolean getRequireComment() {
			return requireComment;
		}

		public Status setName(Enum status) {
			if (isValidHandelseStatus(status))
				this.name = status;
			return this;
		}

		public Status setRequireComment(Boolean requireComment) {
			this.requireComment = requireComment;
			return this;
		}
	}
}
