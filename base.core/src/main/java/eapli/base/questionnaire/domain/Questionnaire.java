package eapli.base.questionnaire.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Questionnaire {
    @Transient
    private String id;
    @Transient
    private String title;
    @Transient
    private String welcomeMessage;
    @Transient
    private List<Section> sections;
    @Transient
    private String finalMessage;

    protected Questionnaire() {
        //ORM
    }


    protected String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    protected String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    protected List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getFinalMessage() {
        return finalMessage;
    }

    public void setFinalMessage(String finalMessage) {
        this.finalMessage = finalMessage;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", welcomeMessage='" + welcomeMessage + '\'' +
                ", sections=" + sections +
                ", finalMessage='" + finalMessage + '\'' +
                '}';
    }

    public Questionnaire(String id, String title, String welcomeMessage, List<Section> sections, String finalMessage) {
        this.id = id;
        this.title = title;
        this.welcomeMessage = welcomeMessage;
        this.sections = sections;
        this.finalMessage = finalMessage;
    }

}
