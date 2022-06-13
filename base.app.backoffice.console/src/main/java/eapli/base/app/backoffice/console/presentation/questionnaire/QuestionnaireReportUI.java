package eapli.base.app.backoffice.console.presentation.questionnaire;

import eapli.base.grammar.AnswerVisitor;
import eapli.base.grammar.LabeledExprLexer;
import eapli.base.grammar.LabeledExprParser;
import eapli.base.questionnaire.application.QuestionnaireReportController;
import eapli.base.questionnaire.domain.Answer;
import eapli.base.questionnaire.domain.QuestionnaireReport;
import eapli.base.questionnaire.domain.Survey;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class QuestionnaireReportUI extends AbstractUI {

    private final QuestionnaireReportController controller = new QuestionnaireReportController();

    @Override
    protected boolean doShow() {
        final Iterable <Survey> surveys = controller.getSurveys();
        final SelectWidget<Survey> selector = new SelectWidget<>("Surveys:", surveys,
                new SurveyPrinter());
        selector.show();
        Survey selectSurvey = selector.selectedElement();
        List<Answer> answers = (List<Answer>) controller.getAnswersByQuestionnaireId(selectSurvey.alphaNumericCode());
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("base.core/src/main/java/eapli/base/grammar/teste.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        LabeledExprLexer lexer = null;
        try {
            lexer = new LabeledExprLexer(new ANTLRInputStream(fis));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokens);
        ParseTree tree = parser.prog();
        AnswerVisitor eval = new AnswerVisitor();
        eval.questionnaireAnswers(answers);
        eval.visit(tree);

        List <Integer> universe = controller.universe();

        QuestionnaireReport qr = new QuestionnaireReport(universe.get(0),universe.get(1),eval.singleChoice(),eval.multipleChoice(),eval.sortingOptions(),eval.scalingOptions());
        System.out.println(qr.reportPrint());

        return false;
    }

    @Override
    public String headline() {
        return "Survey";
    }
}
