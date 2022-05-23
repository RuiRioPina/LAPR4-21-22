package eapli.base.questionnaire.domain;

public class Question {
    private String id;
    private String questionMessage;
    private String instruction;
    private QuestionType type;
    private Obligatoriness obligatoriness;
    private String extraInfo;

    public Question(String id, String questionMessage, String instruction,QuestionType type, Obligatoriness obligatoriness, String extraInfo) {
        this.id = id;
        this.questionMessage = questionMessage;
        this.type = type;
        this.obligatoriness = obligatoriness;
        this.extraInfo = extraInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionMessage() {
        return questionMessage;
    }

    public void setQuestionMessage(String questionMessage) {
        this.questionMessage = questionMessage;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Obligatoriness getObligatoriness() {
        return obligatoriness;
    }

    public void setObligatoriness(Obligatoriness obligatoriness) {
        this.obligatoriness = obligatoriness;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
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
}
