package eapli.base.questionnaire.domain;

import eapli.base.productCategory.domain.AlphaNumericCode;
import eapli.base.questionnaire.dto.SurveyDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Description;
import eapli.framework.representations.dto.DTOable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Survey implements AggregateRoot<AlphaNumericCode>, DTOable<SurveyDTO> {
    @Id
    @Column(nullable = false)
    private AlphaNumericCode alphaNumericCode;

    @Embedded
    private Description description;

    @Embedded
    @Column(name = "period_in_days")
    private Period period;

    @Embedded
    private Questionnaire questionnaire;

    @Embedded
    private Content content;

    protected Survey() {
        //ORM
    }


    @Override
    public boolean sameAs(Object other) {
        Survey otherSurvey = (Survey) other;
        return alphaNumericCode.equals(otherSurvey.alphaNumericCode);
    }

    @Override
    public AlphaNumericCode identity() {
        return alphaNumericCode;
    }

    public AlphaNumericCode getAlphaNumericCode() {
        return alphaNumericCode;
    }

    public void setAlphaNumericCode(AlphaNumericCode alphaNumericCode) {
        this.alphaNumericCode = alphaNumericCode;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        if(questionnaire!= null) {
            return "Survey{" +
                    "alphaNumericCode=" + alphaNumericCode +
                    ", description=" + description +
                    ", period=" + period +
                    ", questionnaire=" + questionnaire +
                    ", content=" + content +
                    '}';
        }else{
            return "Survey{" +
                    "alphaNumericCode=" + alphaNumericCode +
                    ", description=" + description +
                    ", period=" + period +
                    ", content=" + content +
                    '}';
        }
    }

    public Survey(AlphaNumericCode alphaNumericCode, Description description, Period period, Questionnaire questionnaire) {
        this.alphaNumericCode = alphaNumericCode;
        this.description = description;
        this.period = period;
        this.questionnaire = questionnaire;
        this.content = new Content(questionnaire);
    }
    public Survey(AlphaNumericCode alphaNumericCode, Description description, Period period ) {
        this.alphaNumericCode = alphaNumericCode;
        this.description = description;
        this.period = period;
    }

    public Survey(AlphaNumericCode alphaNumericCode, Description description, Period period, Content content) {
        this.alphaNumericCode = alphaNumericCode;
        this.description = description;
        this.period = period;
        this.content = content;
    }


    /**
     * Showcase the {@link DTOable} interface. In this case it is the Dish class
     * that dictates the DTO structure.
     *
     *
     */
    @Override
    public SurveyDTO toDTO() {
        return new SurveyDTO(alphaNumericCode.code(),
                description.toString(),
                period.toString());
    }

}
