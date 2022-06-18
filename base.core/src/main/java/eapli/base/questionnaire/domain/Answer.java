package eapli.base.questionnaire.domain;


import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.productCategory.domain.AlphaNumericCode;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class Answer implements AggregateRoot<AlphaNumericCode> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String answer;
    private String sectionID;
    private String questionID;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "survey_alphanumericcode", referencedColumnName = "alphanumericcode")
    })
    private Survey survey;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Answer{");
        sb.append("id=").append(id);
        sb.append(", answer='").append(answer).append('\'');
        sb.append(", sectionID='").append(sectionID).append('\'');
        sb.append(", questionID='").append(questionID).append('\'');
        sb.append(", survey=").append(survey);
        sb.append(", customer=").append(customer);
        sb.append('}');
        return sb.toString();
    }

    @OneToOne
    @JoinColumn(name = "customer_customer_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestionID() {
        return questionID;
    }

    public Answer(String answer, String sectionID, String questionID, Customer customer, Survey survey) {
        this.answer = answer;
        this.sectionID = sectionID;
        this.questionID = questionID;
        this.customer = customer;
        this.survey = survey;
    }

    protected Answer() {

    }

    public String answer() {
        return answer;
    }

    public String questionID() {
        return questionID;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public AlphaNumericCode identity() {
        return null;
    }
}