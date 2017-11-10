// Generated from Calculette.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalculetteParser}.
 */
public interface CalculetteListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull CalculetteParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull CalculetteParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#assignation}.
	 * @param ctx the parse tree
	 */
	void enterAssignation(@NotNull CalculetteParser.AssignationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#assignation}.
	 * @param ctx the parse tree
	 */
	void exitAssignation(@NotNull CalculetteParser.AssignationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(@NotNull CalculetteParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(@NotNull CalculetteParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#signed}.
	 * @param ctx the parse tree
	 */
	void enterSigned(@NotNull CalculetteParser.SignedContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#signed}.
	 * @param ctx the parse tree
	 */
	void exitSigned(@NotNull CalculetteParser.SignedContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(@NotNull CalculetteParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(@NotNull CalculetteParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(@NotNull CalculetteParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(@NotNull CalculetteParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#bloc}.
	 * @param ctx the parse tree
	 */
	void enterBloc(@NotNull CalculetteParser.BlocContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#bloc}.
	 * @param ctx the parse tree
	 */
	void exitBloc(@NotNull CalculetteParser.BlocContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(@NotNull CalculetteParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(@NotNull CalculetteParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#finInstruction}.
	 * @param ctx the parse tree
	 */
	void enterFinInstruction(@NotNull CalculetteParser.FinInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#finInstruction}.
	 * @param ctx the parse tree
	 */
	void exitFinInstruction(@NotNull CalculetteParser.FinInstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(@NotNull CalculetteParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(@NotNull CalculetteParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#calcul}.
	 * @param ctx the parse tree
	 */
	void enterCalcul(@NotNull CalculetteParser.CalculContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#calcul}.
	 * @param ctx the parse tree
	 */
	void exitCalcul(@NotNull CalculetteParser.CalculContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(@NotNull CalculetteParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(@NotNull CalculetteParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#fonction}.
	 * @param ctx the parse tree
	 */
	void enterFonction(@NotNull CalculetteParser.FonctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#fonction}.
	 * @param ctx the parse tree
	 */
	void exitFonction(@NotNull CalculetteParser.FonctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#atome}.
	 * @param ctx the parse tree
	 */
	void enterAtome(@NotNull CalculetteParser.AtomeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#atome}.
	 * @param ctx the parse tree
	 */
	void exitAtome(@NotNull CalculetteParser.AtomeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#boucle}.
	 * @param ctx the parse tree
	 */
	void enterBoucle(@NotNull CalculetteParser.BoucleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#boucle}.
	 * @param ctx the parse tree
	 */
	void exitBoucle(@NotNull CalculetteParser.BoucleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#priority0Expression}.
	 * @param ctx the parse tree
	 */
	void enterPriority0Expression(@NotNull CalculetteParser.Priority0ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#priority0Expression}.
	 * @param ctx the parse tree
	 */
	void exitPriority0Expression(@NotNull CalculetteParser.Priority0ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculetteParser#boucleElse}.
	 * @param ctx the parse tree
	 */
	void enterBoucleElse(@NotNull CalculetteParser.BoucleElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculetteParser#boucleElse}.
	 * @param ctx the parse tree
	 */
	void exitBoucleElse(@NotNull CalculetteParser.BoucleElseContext ctx);
}