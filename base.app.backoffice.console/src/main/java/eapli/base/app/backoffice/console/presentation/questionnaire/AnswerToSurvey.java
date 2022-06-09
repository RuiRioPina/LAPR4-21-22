package eapli.base.app.backoffice.console.presentation.questionnaire;

import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.grammar.EvalVisitor;
import eapli.base.grammar.LabeledExprLexer;
import eapli.base.grammar.LabeledExprParser;
import eapli.base.questionnaire.application.SurveyController;
import eapli.base.questionnaire.domain.Answer;
import eapli.base.questionnaire.domain.Survey;
import eapli.base.usermanagement.application.AddCustomerController;
import eapli.framework.presentation.console.AbstractUI;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AnswerToSurvey extends AbstractUI {

    SurveyController surveyController = new SurveyController();
    AddCustomerController customerController = new AddCustomerController();
    @Override
    protected boolean doShow() {
        SurveyController surveyController = new SurveyController();

//        Optional<SurveyDTO> surveyDTO = surveyController.surveyToBeAnswered();
//
//        if (surveyDTO.isEmpty()) {
//            return false;
//        }

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("base.core/src/main/java/eapli/base/grammar/teste.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        LabeledExprLexer lexer = null;
        try {
            lexer = new LabeledExprLexer(new ANTLRInputStream(fis));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokens);
        ParseTree tree = parser.prog();
        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);
        List<Answer> answerList = eval.getAnswers();
        Survey survey = surveyController.surveyToBeAnswered().get();

        survey.setAnswers(answerList);
        surveyController.saveSurvey(survey);

        return false;
    }

    @Override
    public String headline() {
        return "Answer survey";
    }
}
