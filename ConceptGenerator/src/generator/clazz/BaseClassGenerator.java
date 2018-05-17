package generator.clazz;

import java.util.HashSet;
import java.util.Set;

import antlr.ConceptBaseVisitor;
import antlr.ConceptParser.CompilationUnitContext;
import antlr.ConceptParser.ConceptAttributeContext;
import antlr.ConceptParser.ConceptBodyContext;
import antlr.ConceptParser.ConceptDeclarationContext;
import antlr.ConceptParser.DataAttributeContext;
import antlr.ConceptParser.SuperConceptContext;
import util.FileUtils;

public class BaseClassGenerator extends AbstractConceptVisitor {

	@Override
	public String visitCompilationUnit(CompilationUnitContext ctx) {

		StringBuilder sb = new StringBuilder();

		for (ConceptDeclarationContext cdc : ctx.conceptDeclaration()) {
			sb.append(visit(cdc) + "\n\n");
		}

		return sb.toString();
	}

	@Override
	public String visitConceptDeclaration(ConceptDeclarationContext ctx) {

		StringBuilder sb = new StringBuilder();
		sb.append("package " + FileUtils.BASEMODEL_PACKAGE + ";\n\n");
		if (needsListImport(ctx)) {
			sb.append("import java.util.*;\n");
		}
		sb.append("import javax.persistence.*;\n");
		if(ctx.superConcept() == null) {
			sb.append("import concept.predefined.BaseEntity;\n");	
		}
		sb.append(getModelImports(ctx));
		sb.append("\n");
		sb.append("@MappedSuperclass\n");
		sb.append("@Inheritance(strategy=InheritanceType.JOINED)\n");
		sb.append("public class " + ctx.Identifier().getText() + "Base extends " + getExtendedClass(ctx) + "{\n\n");
		sb.append(visit(ctx.conceptBody()));
		sb.append("}");

		createFile(FileUtils.BASEMODEL_FOLDER, ctx.Identifier().getText() + "Base.java", sb.toString(), true);

		return sb.toString();

	}

	private String getExtendedClass(ConceptDeclarationContext ctx) {
		if (ctx.superConcept() != null) {
			return ctx.superConcept().referenceType().Identifier().getText();
		}
		return "BaseEntity";
	}

	private String getModelImports(ConceptDeclarationContext ctx) {
		ConceptBaseVisitor<Set<String>> neededClassesVisitor = new ConceptBaseVisitor<Set<String>>() {
			
			@Override
			protected Set<String> defaultResult() {
				return null;
			}

			@Override
			protected Set<String> aggregateResult(Set<String> aggregate, Set<String> nextResult) {
				if (aggregate == null) {
					return nextResult;
				}
				if (nextResult == null) {
					return aggregate;
				}
				aggregate.addAll(nextResult);
				return aggregate;
			}

			@Override
			public Set<String> visitConceptAttribute(ConceptAttributeContext ctx) {
				Set<String> nameSet = new HashSet<>();
				nameSet.add(ctx.referenceType().Identifier().getText());
				return nameSet;
			}
			
			@Override
			public Set<String> visitSuperConcept(SuperConceptContext ctx) {
				Set<String> nameSet = new HashSet<>();
				nameSet.add(ctx.referenceType().Identifier().getText());
				return nameSet;
			}
		};
		Set<String> classNames = neededClassesVisitor.visit(ctx);
		if (classNames != null) {
			StringBuilder imports = new StringBuilder();
			for (String className : classNames) {
				imports.append("import " + FileUtils.MODEL_PACKAGE + "." + className + ";\n");
			}
			return imports.toString();
		}
		return "";
	}

	private boolean needsListImport(ConceptDeclarationContext ctx) {
		ConceptBaseVisitor<Boolean> needsListVisitor = new ConceptBaseVisitor<Boolean>() {

			@Override
			protected Boolean defaultResult() {
				return false;
			}

			@Override
			protected Boolean aggregateResult(Boolean aggregate, Boolean nextResult) {
				return aggregate || nextResult;
			}

			@Override
			public Boolean visitDataAttribute(DataAttributeContext ctx) {
				return ctx.LIST() != null;
			}

			@Override
			public Boolean visitConceptAttribute(ConceptAttributeContext ctx) {
				return ctx.LIST() != null;
			}
		};
		Boolean result = needsListVisitor.visit(ctx);
		return result != null ? result : false;
	}

	@Override
	public String visitConceptBody(ConceptBodyContext ctx) {

		StringBuilder sb = new StringBuilder();

		AttributeGenerator attrGen = new AttributeGenerator();
		sb.append(attrGen.visit(ctx));

		GetterSetterGenerator getSetGen = new GetterSetterGenerator();
		sb.append(getSetGen.visit(ctx));

		return sb.toString();
	}
}
