// Generated from C:/Users/Asus/Documents/lei21_22_s4_2dh_05/base.core/src/main/java/eapli/base/grammar\LabeledExpr.g4 by ANTLR 4.10.1
package eapli.base.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LabeledExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LabeledExprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LabeledExprParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(LabeledExprParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link LabeledExprParser#info}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfo(LabeledExprParser.InfoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LabeledExprParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(LabeledExprParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LabeledExprParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(LabeledExprParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LabeledExprParser#questionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionType(LabeledExprParser.QuestionTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LabeledExprParser#free_text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFree_text(LabeledExprParser.Free_textContext ctx);
	/**
	 * Visit a parse tree produced by {@link LabeledExprParser#multipleChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleChoice(LabeledExprParser.MultipleChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link LabeledExprParser#singleChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleChoice(LabeledExprParser.SingleChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link LabeledExprParser#numeric}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric(LabeledExprParser.NumericContext ctx);
}