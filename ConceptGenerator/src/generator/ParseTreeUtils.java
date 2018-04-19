package generator;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import antlr.ConceptParser.AttributeContext;
import antlr.ConceptParser.CompilationUnitContext;
import antlr.ConceptParser.ConceptAttributeContext;
import antlr.ConceptParser.ConceptDeclarationContext;

public class ParseTreeUtils {

	/**
	 * Sucht im ParseTree nach dem ConceptDeclarationContext
	 * 
	 * @param name Der Name des Concepts
	 * @param tree Eine beliebige stelle im ParseTree
	 * @return Den ConceptDeclarationContext mit dem Identifier name
	 */
	public static ConceptDeclarationContext getConceptDeclaration(String name, ParseTree tree) {
		ParseTree root = getRoot(tree);
		if (!(root instanceof CompilationUnitContext)) {
			throw new IllegalStateException("The Root is no compilationUnit");
		}
		CompilationUnitContext ctx = (CompilationUnitContext) root;
		for (ConceptDeclarationContext concept : ctx.conceptDeclaration()) {
			if (concept.Identifier().getText().equals(name)) {
				return concept;
			}
		}
		throw new IllegalArgumentException("Concept not found: " + name);
	}

	/**
	 * Sucht Rekursiv nach der Wurzel des ParseTrees
	 * 
	 * @param tree Der ParseTree
	 * @return Die Wurzel des ParseTree
	 */
	public static ParseTree getRoot(ParseTree tree) {
		ParseTree parent = tree.getParent();
		if (parent == null) {
			return tree;
		}
		return getRoot(parent);
	}

	/**
	 * Sucht im gegebenen ConceptDeclarationContext nach einem Attribut mit dem Typ targetType
	 * 
	 * @param concept Der ConceptDeclarationContext, in dem gesucht wird
	 * @param targetType Der gesuchte Typ
	 * @param referencingType Der referenzierte Typ
	 * @return Das gefundene ConceptAttributeContext das den typ targetType hat und referencingType referenziert
	 */
	public static ConceptAttributeContext getConceptAttributeFor(ConceptDeclarationContext concept, String targetType, String referencingType) {
		for (AttributeContext attr : concept.conceptBody().attribute()) {
			try {
				ConceptAttributeContext cAttrCtx = attr.simpleAttribute().conceptAttribute();
				if (cAttrCtx.referenceType().Identifier().getText().equals(targetType)) {
					if (cAttrCtx.relation().Identifier().getText().equals(referencingType)) {
						return cAttrCtx;
					}
				}
			} catch (NullPointerException e) {
				// If attr is no concept Attribute
			}
		}
		return null;
	}

	/**
	 * Sucht im ParseTree nach dem ersten Vaterknoten der angegebenen Klasse
	 * 
	 * @param current Der Startknoten
	 * @param search Die Klasse, die der Vaterknoten haben soll
	 * @return Den ersten Vaterknoten der Klasse search
	 */
	public static <T extends ParserRuleContext> T getSurrounding(ParseTree current, Class<T> search) {
		if (current == null) {
			throw new IllegalArgumentException("Null");
		}
		if (search.isInstance(current)) {
			return search.cast(current);
		}
		return getSurrounding(current.getParent(), search);
	}
}
