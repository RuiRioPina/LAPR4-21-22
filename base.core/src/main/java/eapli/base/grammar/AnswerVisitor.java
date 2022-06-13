package eapli.base.grammar;

import eapli.base.questionnaire.domain.Answer;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerVisitor extends LabeledExprBaseVisitor<String> {


    private final Map<String,List<List<String>>> singleChoice = new HashMap<>();
    private final Map<List<String>,List<List<String>>> multipleChoice = new HashMap<>();
    private final Map<List<String>,List<List<String>>> sortingOptions = new HashMap<>();
    private final Map<List<String>,List<List<String>>> scalingOptions = new HashMap<>();
    private Answer answerSingleChoice;
    private final List<String> answerListMultipleChoice = new ArrayList<>();
    private final List<String> answerListSortingOptions = new ArrayList<>();
    private final List<String> answerListScalingOptions = new ArrayList<>();
    private List<Answer> answerList;
    private String questionID;

    public void questionnaireAnswers(List<Answer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public String visitSection(LabeledExprParser.SectionContext ctx) {
        visitChildren(ctx);
        return "Section";
    }

    @Override
    public String visitQuestion(LabeledExprParser.QuestionContext ctx) {
        questionID = ctx.QUESTION_ID().toString();
        visitChildren(ctx);
        return "Question";
    }

    @Override
    public String visitScalingOptions(LabeledExprParser.ScalingOptionsContext ctx){
        getContextAnswersScalingOptions();
        if (scalingOptions.containsKey(answerListScalingOptions)) {
            List <List<String>> opt = sortingOptions.get(answerListScalingOptions);
            scalingOptions.remove(answerListScalingOptions);
            opt.add(getAnswerOptionsScalingOptions(ctx));
            scalingOptions.put(answerListScalingOptions, opt);
        } else {
            List<List<String>> opt = new ArrayList<>();
            opt.add(getAnswerOptionsScalingOptions(ctx));
            scalingOptions.put(answerListScalingOptions, opt);
        }
        return ctx.getText();
    }

    public List <String> getAnswerOptionsScalingOptions (LabeledExprParser.ScalingOptionsContext ctx) {
        List <String> options = new ArrayList<>();
        for (TerminalNode option : ctx.CHOOSE()){
            options.add(String.valueOf(option.toString().charAt(0)));
        }
        return options;
    }

    public void getContextAnswersScalingOptions () {
        for (Answer a : answerList) {
            if (a.questionID().equals(questionID)) {
                answerListScalingOptions.add(a.answer());
            }
        }
    }

    //--------SORTING OPTIONS---------//

    @Override
    public String visitSortingOptions(LabeledExprParser.SortingOptionsContext ctx){
        getContextAnswersSortingOptions();
        if (sortingOptions.containsKey(answerListSortingOptions)) {
            List <List<String>> opt = sortingOptions.get(answerListSortingOptions);
            sortingOptions.remove(answerListSortingOptions);
            opt.add(getAnswerOptionsSortingOptions(ctx));
            sortingOptions.put(answerListSortingOptions, opt);
        } else {
            List<List<String>> opt = new ArrayList<>();
            opt.add(getAnswerOptionsSortingOptions(ctx));
            sortingOptions.put(answerListSortingOptions, opt);
        }
        return ctx.getText();
    }

    public List <String> getAnswerOptionsSortingOptions (LabeledExprParser.SortingOptionsContext ctx) {
        List <String> options = new ArrayList<>();
        for (TerminalNode option : ctx.CHOOSE()){
            options.add(String.valueOf(option.toString().charAt(0)));
        }
        return options;
    }

    public void getContextAnswersSortingOptions () {
        for (Answer a : answerList) {
            if (a.questionID().equals(questionID)) {
                answerListSortingOptions.add(a.answer());
            }
        }
    }

    //--------MULTIPLE CHOICE---------//

    @Override
    public String visitMultipleChoice(LabeledExprParser.MultipleChoiceContext ctx) {
        getContextAnswersMultipleChoice();
        if (multipleChoice.containsKey(answerListMultipleChoice)) {
            List <List<String>> opt = multipleChoice.get(answerListMultipleChoice);
            multipleChoice.remove(answerListMultipleChoice);
            opt.add(getAnswerOptionsMultipleChoice(ctx));
            multipleChoice.put(answerListMultipleChoice, opt);
        } else {
            List<List<String>> opt = new ArrayList<>();
            opt.add(getAnswerOptionsMultipleChoice(ctx));
            multipleChoice.put(answerListMultipleChoice, opt);
        }
        return ctx.getText();
    }

    public List <String> getAnswerOptionsMultipleChoice (LabeledExprParser.MultipleChoiceContext ctx) {
        List <String> options = new ArrayList<>();
        for (TerminalNode option : ctx.CHOOSE()){
            options.add(String.valueOf(option.toString().charAt(0)));
        }
        return options;
    }

    public void getContextAnswersMultipleChoice () {
        for (Answer a : answerList) {
            if (a.questionID().equals(questionID)) {
                answerListMultipleChoice.add(a.answer());
            }
        }
    }

    //--------SINGLE CHOICE---------//

    @Override
    public String visitSingleChoice(LabeledExprParser.SingleChoiceContext ctx) {
        getContextAnswer();
        if (singleChoice.containsKey(answerSingleChoice.answer())) {
            List <List<String>> opt = singleChoice.get(answerSingleChoice.answer());
            singleChoice.remove(answerSingleChoice.answer());
            opt.add(getAnswerOptionsSingleChoice(ctx));
            singleChoice.put(answerSingleChoice.answer(), opt);
        } else {
            List<List<String>> opt = new ArrayList<>();
            opt.add(getAnswerOptionsSingleChoice(ctx));
            singleChoice.put(answerSingleChoice.answer(), opt);
        }
        return ctx.getText();
    }

    public List <String> getAnswerOptionsSingleChoice (LabeledExprParser.SingleChoiceContext ctx) {
        List <String> options = new ArrayList<>();
        for (TerminalNode option : ctx.CHOOSE()){
            options.add(String.valueOf(option.toString().charAt(0)));
        }
        return options;
    }


    public void getContextAnswer() {
        for (Answer a : answerList) {
            if (a.questionID().equals(questionID)) {
                answerSingleChoice = a;
                break;
            }
        }
    }

    public Map<String, List<List<String>>> singleChoice() {
        return singleChoice;
    }

    public Map<List<String>, List<List<String>>> multipleChoice() {
        return multipleChoice;
    }

    public Map<List<String>, List<List<String>>> sortingOptions() {
        return sortingOptions;
    }

    public Map<List<String>, List<List<String>>> scalingOptions() {
        return scalingOptions;
    }

}
