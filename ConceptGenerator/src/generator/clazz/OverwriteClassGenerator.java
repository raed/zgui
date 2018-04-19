package generator.clazz;

import antlr.ConceptParser.ConceptDeclarationContext;
import util.FileUtils;

public class OverwriteClassGenerator extends AbstractConceptVisitor {

	@Override
	public String visitConceptDeclaration(ConceptDeclarationContext ctx) {

		StringBuilder sb = new StringBuilder();
		sb.append("package " + FileUtils.MODEL_PACKAGE + ";\n\n");
		sb.append("import javax.persistence.*;\n");
		sb.append("import " + FileUtils.BASEMODEL_PACKAGE + "." + getBaseClassName(ctx) + ";\n");
		sb.append("\n");
		sb.append("@Entity\n");
		sb.append("public class " + ctx.Identifier().getText() + " extends " + getBaseClassName(ctx) + "{\n\n");
		sb.append("\n");
		sb.append("}");

		createFile(FileUtils.MODEL_FOLDER, ctx.Identifier().getText() + ".java", sb.toString(), true);
		return sb.toString();
	}

	private String getBaseClassName(ConceptDeclarationContext ctx) {
		return ctx.Identifier().getText() + "Base";
	}
}
