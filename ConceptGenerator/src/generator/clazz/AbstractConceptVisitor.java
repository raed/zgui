package generator.clazz;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import antlr.ConceptBaseVisitor;
import antlr.ConceptParser.CompilationUnitContext;
import antlr.ConceptParser.ConceptAttributeContext;
import antlr.ConceptParser.DataAttributeContext;
import antlr.ConceptParser.PrimitiveTypeContext;
import antlr.ConceptParser.PropertyContext;
import antlr.ConceptParser.PropertyListContext;
import antlr.ConceptParser.ReferenceTypeContext;

public abstract class AbstractConceptVisitor extends ConceptBaseVisitor<String> {

	@Override
	public String visitCompilationUnit(CompilationUnitContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	protected String defaultResult() {
		return "";
	}

	@Override
	protected String aggregateResult(String aggregate, String nextResult) {
		return aggregate + nextResult;
	}

	@Override
	public final String visitReferenceType(ReferenceTypeContext ctx) {
		return ctx.Identifier().getText();
	}

	@Override
	public final String visitPrimitiveType(PrimitiveTypeContext ctx) {
		if (ctx.getText().equals("string")) {
			return "String";
		}
		return ctx.getText();
	}

	public static String firstLetterCap(String input) {
		if (input == null) {
			return null;
		}
		String output = input.substring(0, 1).toUpperCase();
		if (input.length() > 1) {
			output += input.substring(1);
		}
		return output;
	}

	public String calculateTyp(ConceptAttributeContext ctx) {
		String typ = visit(ctx.referenceType());
		if (ctx.LIST() != null) {
			typ = "List<" + typ + ">";
		}
		return typ;
	}

	public String calculateTyp(DataAttributeContext ctx) {
		String typ = visit(ctx.primitiveType());
		if (ctx.LIST() != null) {
			typ = "List<" + changePrimitiveToWrapper(typ) + ">";
		}
		return typ;
	}

	public static String changePrimitiveToWrapper(String primitive) {
		switch (primitive) {
		case "int":
			return "Integer";
		case "boolean":
			return "Boolean";
		case "double":
			return "Double";
		case "float":
			return "Float";
		case "char":
			return "Char";
		case "long":
			return "Long";
		case "byte":
			return "Byte";
		case "short":
			return "Short";
		default:
			throw new IllegalArgumentException("Unknown primitive: " + primitive);
		}
	}

	@Deprecated
	public static boolean containsProperty(PropertyListContext plc, int property) {
		if (plc == null) {
			return false;
		}
		for (PropertyContext pc : plc.property()) {
			if (pc.start.getType() == property) {
				return true;
			}
		}
		return false;
	}

	protected void createFile(File folder, String fileName, String content, boolean overwrite) {
		File file = new File(folder, fileName);
		if (file.exists() && !overwrite) {
			System.out.println(file.getName() + " existiert bereits -> Nicht ueberschrieben");
			return;
		}
		file.delete();
		try {
			file.createNewFile();
			PrintStream fps = new PrintStream(file);
			fps.print(content);
			fps.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
