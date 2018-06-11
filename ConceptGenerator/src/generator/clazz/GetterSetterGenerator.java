package generator.clazz;

import antlr.ConceptParser.AttributeContext;
import antlr.ConceptParser.ConceptAttributeContext;
import antlr.ConceptParser.ConceptBodyContext;
import antlr.ConceptParser.ConceptDeclarationContext;
import antlr.ConceptParser.DataAttributeContext;
import antlr.ConceptParser.FunctionAttributeContext;
import generator.ParseTreeUtils;

public class GetterSetterGenerator extends AbstractConceptVisitor {

	@Override
	public String visitConceptBody(ConceptBodyContext ctx) {
		StringBuilder sb = new StringBuilder();
		for (AttributeContext attrCtx : ctx.attribute()) {
			sb.append(visit(attrCtx));
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public String visitDataAttribute(DataAttributeContext ctx) {
		StringBuilder sb = new StringBuilder();
		String typ = calculateTyp(ctx, false);
		String name = ctx.Identifier().getText();

		sb.append(requireElementCollection(ctx));
		sb.append(generateGetter(typ, name));
		sb.append("\n\n");
		sb.append(generateSetter(typ, name));
		sb.append("\n");
		return sb.toString();
	}

	private String requireElementCollection(DataAttributeContext ctx) {
		if (ctx.LIST() != null) {
			return "\t@ElementCollection\n";
		}
		return "";
	}

	@Override
	public String visitConceptAttribute(ConceptAttributeContext ctx) {
		StringBuilder sb = new StringBuilder();
		String typ = calculateTyp(ctx, false);
		String name = ctx.Identifier().getText();

		sb.append(generateGetter(typ, name, ctx));
		sb.append("\n\n");
		sb.append(generateSetter(typ, name));
		sb.append("\n");
		return sb.toString();
	}

	private String generateGetter(String type, String name, ConceptAttributeContext ctx) {
		return "\t" + resolveMapping(ctx) + "\n" + generateGetter(type, name);
	}

	private String generateGetter(String type, String name) {
		StringBuilder sb = new StringBuilder();
		sb.append("\tpublic " + type + " get" + firstLetterCap(name) + "(){\n");
		sb.append("\t\treturn " + name + ";\n");
		sb.append("\t}");
		return sb.toString();
	}

	private String generateSetter(String type, String name) {
		StringBuilder sb = new StringBuilder();
		sb.append("\tpublic void set" + firstLetterCap(name) + "(" + type + " " + name + "){\n");
		sb.append("\t\tthis." + name + " = " + name + ";\n");
		sb.append("\t}");
		return sb.toString();
	}

	@Override
	public String visitFunctionAttribute(FunctionAttributeContext ctx) {
		String type = visit(ctx.simpleType());
		StringBuilder sb = new StringBuilder();
		sb.append("\t@Transient\n");
		sb.append("\tpublic " + type + " get" + firstLetterCap(ctx.Identifier().getText()) + "(){\n");
		sb.append("\t\treturn " + ctx.expr().getText() + ";");
		sb.append("\n\t}\n");
		return sb.toString();
	}

	private String resolveMapping(ConceptAttributeContext ctx) {
		String targetType = ctx.referenceType().Identifier().getText();
		boolean iHaveList = ctx.LIST() != null;

		ConceptDeclarationContext myConceptDeclaration = ParseTreeUtils.getSurrounding(ctx, ConceptDeclarationContext.class);
		String myType = myConceptDeclaration.Identifier().getText();

		//
		ConceptDeclarationContext targetConceptDeclaration = ParseTreeUtils.getConceptDeclaration(targetType, ctx);
		ConceptAttributeContext targetConceptAttribute = ParseTreeUtils.getConceptAttributeFor(targetConceptDeclaration, myType, ctx.Identifier().getText());

		boolean targetHasList = true;
		String targetAttributeName = null;
		if (targetConceptAttribute != null) {
			targetHasList = targetConceptAttribute.LIST() != null;
			targetAttributeName = targetConceptAttribute.Identifier().getText();
		}

		if (iHaveList) {
			if (targetHasList) {
				return "@ManyToMany\n" + createJoinTableAnnotation(myType, ctx.Identifier().getText(), targetConceptDeclaration.Identifier().getText(), targetAttributeName);
			} else {
				return "@OneToMany(mappedBy = \"" + targetAttributeName + "\")";
			}
		} else {
			if (targetHasList) {
				return "@ManyToOne(cascade = CascadeType.ALL)";
			} else {
				return "@OneToOne(cascade = CascadeType.ALL)";
			}
		}
	}

	private String createJoinTableAnnotation(String type1, String id1, String type2, String id2) {
		String tableName = createJoinTableName(type1, id1, type2, id2);
		String fString = "\t@JoinTable(name = \"%1$s\", joinColumns = { @JoinColumn(name = \"%2$s\") }, inverseJoinColumns = { @JoinColumn(name = \"%3$s\") })";

		// String a = id1 != null ? id1 : type2;
		// String b = id2 != null ? id2 : type1;

		return String.format(fString, tableName, type1.toLowerCase(), type2.toLowerCase());
	}

	private String createJoinTableName(String type1, String id1, String type2, String id2) {
		id1 = id1 != null ? "_" + id1 : "";
		id2 = id2 != null ? "_" + id2 : "";
		String a = type1 + id1;
		String b = type2 + id2;
		a = a.toLowerCase();
		b = b.toLowerCase();
		if (a.compareTo(b) > 0) {
			String temp = a;
			a = b;
			b = temp;
		}
		return a + "_" + b;
	}

}
