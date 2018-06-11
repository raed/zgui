package generator.clazz;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import antlr.ConceptBaseVisitor;
import antlr.ConceptParser.CompilationUnitContext;
import antlr.ConceptParser.ConceptAttributeContext;
import antlr.ConceptParser.DataAttributeContext;
import antlr.ConceptParser.PrimitiveTypeContext;
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
		if (ctx.getText().equals("date")) {
			return "Date";
		}
		if (ctx.getText().equals("datetime")) {
			return "Date";
		}
		return ctx.getText();
	}
	
	public String calculateTyp(ConceptAttributeContext ctx, boolean concrete) {
		String typ = visit(ctx.referenceType());
		if (ctx.LIST() != null) {
			typ = getListType(typ, concrete);
		}
		return typ;
	}

	public String calculateTyp(DataAttributeContext ctx, boolean concrete) {
		String typ = visit(ctx.primitiveType());
		if (ctx.LIST() != null) {
			typ = getListType(typ, concrete);
		}
		return typ;
	}
	
	private static String getListType(String typ, boolean concrete) {
		String wrapperTyp = changePrimitiveToWrapper(typ);
		if (concrete) {
			typ = "ArrayList<" + wrapperTyp + ">";
		} else {
			typ = "List<" + wrapperTyp + ">";
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
			return primitive;
		}
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
