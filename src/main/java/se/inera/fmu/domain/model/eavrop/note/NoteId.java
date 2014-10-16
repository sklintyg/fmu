package se.inera.fmu.domain.model.eavrop.note;

import java.io.Serializable;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import se.inera.fmu.domain.shared.ValueObject;

@Embeddable
public class NoteId  implements ValueObject<NoteId>, Serializable {

	private static final long serialVersionUID = 1L;
	
	//~ Instance fields ================================================================================================

	@Column(name = "NOTE_ID", nullable = false, updatable = false, unique = true)
    private String id;
    
    //~ Constructors ===================================================================================================

	NoteId() {
		this.id = UUID.randomUUID().toString();
	}

	public NoteId(final String noteId){
		this.setNoteId(noteId);;
	}

	
    //~ Property Methods ===============================================================================================

	public String getBestallarId(){
		return this.id;
	}

	private void setNoteId(String noteId) {
		this.id = noteId;
	}

    //~ Other Methods ==================================================================================================


	@Override
	public String toString() {
		return this.id;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteId other = (NoteId) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean sameValueAs(NoteId other) {
        return other != null && this.id.equals(other.id);
    }
}
