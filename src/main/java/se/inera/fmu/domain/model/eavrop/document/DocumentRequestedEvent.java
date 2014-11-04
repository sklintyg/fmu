package se.inera.fmu.domain.model.eavrop.document;


import java.util.UUID;

import org.apache.commons.lang.Validate;

import se.inera.fmu.domain.model.eavrop.ArendeId;
import se.inera.fmu.domain.model.eavrop.EavropEvent;
import se.inera.fmu.domain.shared.DomainEvent;

public class DocumentRequestedEvent extends EavropEvent {
	
	private final String documentId;
	//~ Constructors ===================================================================================================
    
	public DocumentRequestedEvent(final Long eavropId, final String documentId) {
		super(eavropId);
		this.documentId = documentId;
	}

	//~ Property Methods ===============================================================================================

	public String getDocumentId() {
		return documentId;
	}
	
	//~ Other Methods ==================================================================================================
}
