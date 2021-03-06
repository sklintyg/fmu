package se.inera.fmu.application.impl.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import se.inera.fmu.domain.model.eavrop.ArendeId;
import se.inera.fmu.domain.model.eavrop.Interpreter;
import se.inera.fmu.domain.model.eavrop.UtredningType;
import se.inera.fmu.domain.model.eavrop.invanare.Invanare;
import se.inera.fmu.domain.model.eavrop.invanare.medicalexamination.PriorMedicalExamination;
import se.inera.fmu.domain.model.landsting.LandstingCode;
import se.inera.fmu.domain.model.person.Bestallaradministrator;

/**
 * Created by Rasheed on 11/4/14.
 *
 * Command to create a new eavrop.
 */
@Getter
@AllArgsConstructor
public class CreateEavropCommand {

    @NonNull private ArendeId arendeId;
    @NonNull private UtredningType utredningType;
    private String description;
    private String utredningFocus;
    private String additionalInformation;
    private Interpreter interpreter;
    @NonNull private Invanare invanare;
    @NonNull private LandstingCode landstingCode;
    @NonNull private Bestallaradministrator bestallaradministrator;
    private PriorMedicalExamination priorMedicalExamination;
}