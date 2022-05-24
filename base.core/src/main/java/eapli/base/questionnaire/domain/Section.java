package eapli.base.questionnaire.domain;

import eapli.base.questionnaire.dto.SectionDTO;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.general.domain.model.Description;
import eapli.framework.representations.dto.DTOable;

import java.util.List;

public class Section implements DTOable<SectionDTO>, DomainEntity<String> {
    private String id;
    private String title;
    private Description description;
    private Obligatoriness obligatoriness;
    private String repeatability;
    private List<Question> content;

    public Section(String id, String title, Description description, Obligatoriness obligatoriness, String repeatability, List<Question> content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.obligatoriness = obligatoriness;
        this.repeatability = repeatability;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Obligatoriness getObligatoriness() {
        return obligatoriness;
    }

    public void setObligatoriness(Obligatoriness obligatoriness) {
        this.obligatoriness = obligatoriness;
    }

    public String getRepeatability() {
        return repeatability;
    }

    public void setRepeatability(String repeatability) {
        this.repeatability = repeatability;
    }

    public List<Question> getContent() {
        return content;
    }

    public void setContent(List<Question> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description=" + description +
                ", obligatoriness=" + obligatoriness +
                ", repeatability='" + repeatability + '\'' +
                ", content=" + content +
                '}';
    }

    @Override
    public SectionDTO toDTO() {
        return new Section(id, title, description, obligatoriness, repeatability, content).toDTO();
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
