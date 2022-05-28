package eapli.base.questionnaire.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.List;

@Embeddable
public class Content implements ValueObject {
    @Column(name = "questionnaire", length = 100000)
    private String fullQuestionnaire;

    @Transient
    private final StringBuilder stringBuilder = new StringBuilder();


    public Content(Questionnaire questionnaire) {
        buildQuestionnaireSyntax(questionnaire);
        stringBuilder.append("\n");
        stringBuilder.append("LIST OF SECTIONS:\n");
        stringBuilder.append("\n");
        buildSectionSyntax(questionnaire.getSections());

        fullQuestionnaire = stringBuilder.toString();
    }

    public Content(String fullQuestionnaire) {
        this.fullQuestionnaire = fullQuestionnaire;
    }


    private void buildQuestionnaireSyntax(Questionnaire questionnaire) {
        stringBuilder.append(String.format("ID: %s%n", questionnaire.getId()));
        stringBuilder.append(String.format("TITLE: %s%n", questionnaire.getTitle()));
        if (questionnaire.getWelcomeMessage() != null) {
            stringBuilder.append(String.format("WELCOME MESSAGE: %s%n", questionnaire.getWelcomeMessage()));
        }
        stringBuilder.append(String.format("FINAL MESSAGE: %s%n", questionnaire.getFinalMessage()));

    }

    private void buildSectionSyntax(List<Section> sections) {
        for (Section section : sections) {
            stringBuilder.append(String.format("SECTION ID: %s%n", section.getId()));
            stringBuilder.append(String.format("SECTION TITLE: %s%n", section.getTitle()));
            stringBuilder.append(String.format("SECTION DESCRIPTION: %s%n", section.getDescription()));
            stringBuilder.append(String.format("OBLIGATORINESS: %s%n", section.getObligatoriness()));
            if (section.getRepeatability() != null) {
                stringBuilder.append(String.format("REPEATABILITY: %s%n", section.getRepeatability()));
            }
            stringBuilder.append("\nCONTENT:\n");
            for (Question question : section.getContent()) {
                buildQuestionSyntax(question);
            }
            stringBuilder.append("\n");

        }
    }

    private void buildQuestionSyntax(Question question) {
        stringBuilder.append(String.format("%nQUESTION ID: %s%n", question.getId()));
        stringBuilder.append(String.format("Q: %s%n", question.getQuestionMessage()));
        if (question.getInstruction() != null) {
            stringBuilder.append(String.format("INSTRUCTION: %s%n", question.getInstruction()));
        }
        stringBuilder.append(String.format("OBLIGATORINESS: %s%n", question.getObligatoriness()));
        stringBuilder.append(String.format("TYPE: %s%n", question.getType()));
        if (question.getExtraInfo() != null) {
            stringBuilder.append(String.format("EXTRA INFO: %s%n", question.getExtraInfo()));
        }
    }

    @Override
    public String toString() {
        return "Content{" +
                "fullQuestionnaire='" + fullQuestionnaire + '\'' +
                '}';
    }

    protected Content() {
        //ORM
    }
}
