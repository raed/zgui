package generator.clazz;

import antlr.ConceptParser.AggregatingAttributeContext;
import antlr.ConceptParser.AttributeContext;
import antlr.ConceptParser.ConceptAttributeContext;
import antlr.ConceptParser.ConceptBodyContext;
import antlr.ConceptParser.DataAttributeContext;
import antlr.ConceptParser.FunctionAttributeContext;

public class AttributeGenerator extends AbstractConceptVisitor {

	@Override
	public String visitConceptBody(ConceptBodyContext ctx) {
		StringBuilder sb = new StringBuilder();
		for (AttributeContext attrCtx : ctx.attribute()) {
			// if (attrCtx.dataAttribute() != null) {
			// sb.append(visit(attrCtx.dataAttribute()));
			// sb.append("\n");
			// }
			// if (attrCtx.conceptAttribute() != null) {
			// sb.append(visit(attrCtx.conceptAttribute()));
			// sb.append("\n");
			// }
			sb.append(visit(attrCtx));
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	@Override
	public String visitDataAttribute(DataAttributeContext ctx) {
		StringBuilder sb = new StringBuilder();

		String typ = calculateTyp(ctx, false);
		String concreteTyp = calculateTyp(ctx, true);
		sb.append("\tprivate " + typ + " " + ctx.Identifier().getText());
		if(ctx.LIST() != null) {
			sb.append(" = new " + concreteTyp + "()");
		}
		
		sb.append(";");
		return sb.toString();
	}

	@Override
	public String visitConceptAttribute(ConceptAttributeContext ctx) {
		StringBuilder sb = new StringBuilder();

		String typ = calculateTyp(ctx, false);
		String concreteTyp = calculateTyp(ctx, true);

		sb.append("\tprivate " + typ + " " + ctx.Identifier().getText() + " = new " + concreteTyp + "();");
		return sb.toString();
	}

	@Override
	public String visitFunctionAttribute(FunctionAttributeContext ctx) {
		return "";
	}

	@Override
	public String visitAggregatingAttribute(AggregatingAttributeContext ctx) {
		return "";
	}

}
