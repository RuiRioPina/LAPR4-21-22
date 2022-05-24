package eapli.base.questionnaire.domain;

import eapli.base.questionnaire.dto.QuestionDTO;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.representations.dto.DTOable;

public class Question implements DTOable<QuestionDTO>, DomainEntity<String> {
    private final String id;
    private final String questionMessage;
    private final String instruction;
    private final QuestionType type;
    private final Obligatoriness obligatoriness;
    private final String extraInfo;

    public Question(String id, String questionMessage, String instruction,QuestionType type, Obligatoriness obligatoriness, String extraInfo) {
        this.id = id;
        this.questionMessage = questionMessage;
        this.instruction = instruction;
        this.type = type;
        this.obligatoriness = obligatoriness;
        this.extraInfo = extraInfo;
    }

    public String getId() {
        return id;
    }


    public String getQuestionMessage() {
        return questionMessage;
    }


    public QuestionType getType() {
        return type;
    }


    public Obligatoriness getObligatoriness() {
        return obligatoriness;
    }


    public String getExtraInfo() {
        return extraInfo;
    }

    public String getInstruction() {
        return instruction;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", question='" + questionMessage + '\'' +
                ", type=" + type +
                ", obligatoriness=" + obligatoriness +
                ", extraInfo='" + extraInfo + '\'' +
                '}';
    }

    @Override
    public QuestionDTO toDTO() {
        return new QuestionDTO(id, questionMessage, instruction, type.toString(), obligatoriness.toString(), extraInfo);
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public String identity() {
        return null;
    }
}
