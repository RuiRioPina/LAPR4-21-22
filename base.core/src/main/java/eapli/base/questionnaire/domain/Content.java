package eapli.base.questionnaire.domain;

import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
public class Content {
    private String fullQuestionnaire;

    private final StringBuilder stringBuilder = new StringBuilder();


    public Content(Questionnaire questionnaire) {
        buildQuestionnaireSyntax(questionnaire);
        stringBuilder.append("\n");
        stringBuilder.append("List of Sections {\n");
        stringBuilder.append("\n");
        stringBuilder.append("SECTION [\n");
        buildSectionSyntax(questionnaire.getSections());
        stringBuilder.append("}\n");

        fullQuestionnaire = stringBuilder.toString();
    }

    public Content(String fullQuestionnaire) {
        this.fullQuestionnaire = fullQuestionnaire;
    }


    private void buildQuestionnaireSyntax(Questionnaire questionnaire) {
        stringBuilder.append(String.format("ID: %s%n", questionnaire.getId()));
        stringBuilder.append(String.format("TITLE: %s%n", questionnaire.getTitle()));
        stringBuilder.append(String.format("WELCOME MESSAGE: %s%n", questionnaire.getWelcomeMessage()));
    }

    private void buildSectionSyntax(List<Section> sections) {
        for (Section section : sections) {
            stringBuilder.append(String.format("SECTION ID: %s%n", section.getId()));
            stringBuilder.append(String.format("SECTION TITLE: %s%n", section.getTitle()));
            stringBuilder.append(String.format("SECTION DESCRIPTION: %s%n", section.getDescription()));
            stringBuilder.append(String.format("OBLIGATORINESS: %s%n", section.getObligatoriness()));
            stringBuilder.append("CONTENT [\n");
            for (Question question : section.getContent()) {
                buildQuestionSyntax(question);
            }
            stringBuilder.append("]\n");
            stringBuilder.append("]\n");
            stringBuilder.append("\n");

        }
    }

    private void buildQuestionSyntax(Question question) {
        stringBuilder.append(String.format("%nQUESTION {%n"));
        stringBuilder.append(String.format("QUESTION ID: %s%n", question.getId()));
        stringBuilder.append(String.format("Q: %s%n", question.getQuestionMessage()));
        stringBuilder.append(String.format("TYPE: %s%n", question.getType()));
        stringBuilder.append(String.format("OBLIGATORINESS: %s%n", question.getObligatoriness()));
        stringBuilder.append(String.format("EXTRA INFO: %s%n", question.getExtraInfo()));
        stringBuilder.append("}\n");
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
