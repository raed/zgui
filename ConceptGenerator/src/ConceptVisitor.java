// Generated from Concept.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ConceptParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ConceptVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ConceptParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(ConceptParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#packageDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageDeclaration(ConceptParser.PackageDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#packageName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageName(ConceptParser.PackageNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#importDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDeclaration(ConceptParser.ImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#conceptDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConceptDeclaration(ConceptParser.ConceptDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#conceptModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConceptModifier(ConceptParser.ConceptModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#superConcept}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperConcept(ConceptParser.SuperConceptContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#includeConcept}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncludeConcept(ConceptParser.IncludeConceptContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#conceptBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConceptBody(ConceptParser.ConceptBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(ConceptParser.AttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#simpleAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleAttribute(ConceptParser.SimpleAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#complexAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexAttribute(ConceptParser.ComplexAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#dataAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataAttribute(ConceptParser.DataAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#conceptAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConceptAttribute(ConceptParser.ConceptAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleType(ConceptParser.SimpleTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#chainAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChainAttribute(ConceptParser.ChainAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#functionAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionAttribute(ConceptParser.FunctionAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(ConceptParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#aggregatingAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregatingAttribute(ConceptParser.AggregatingAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange(ConceptParser.RangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#propertyList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyList(ConceptParser.PropertyListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(ConceptParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation(ConceptParser.RelationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#referenceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceType(ConceptParser.ReferenceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(ConceptParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConceptParser#qualifiedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedName(ConceptParser.QualifiedNameContext ctx);
}