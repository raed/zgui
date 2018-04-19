package generator;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import antlr.ConceptLexer;
import antlr.ConceptParser;
import generator.clazz.BaseClassGenerator;
import generator.clazz.OverwriteClassGenerator;
import util.FileUtils;

public class GenerateMain {

	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException {

		System.out.println("Parsing file");
		CharStream inputStream = CharStreams.fromFileName("Person.concept");
		ConceptLexer lexer = new ConceptLexer(inputStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ConceptParser parser = new ConceptParser(tokens);

		System.out.println("(Re)creating Folder Structure");
		deleteFolders();
		createFolders();
		System.out.println("Copying Files");
		// copyPredefinedFiles();
		// copyPredefinedSrc();

		System.out.println("Generating BaseClasses");
		BaseClassGenerator classGenerator = new BaseClassGenerator();
		classGenerator.visit(parser.compilationUnit());

		parser.reset();

		System.out.println("Generating ModelClasses");
		OverwriteClassGenerator overClassGenerator = new OverwriteClassGenerator();
		overClassGenerator.visit(parser.compilationUnit());

		System.out.println("Generation finished");
	}

	private static void createFolders() throws IllegalArgumentException, IllegalAccessException {
		FileUtils.generateAllFolders();
	}

	private static void deleteFolders() {
		deleteFolder(FileUtils.BASEMODEL_FOLDER);
	}

	// private static void copyPredefinedFiles() throws IllegalArgumentException, IllegalAccessException, IOException {
	// Settings.copyPredefinedFiles();
	// }
	//
	// private static void copyPredefinedSrc() throws IOException {
	// Settings.copyPredefinedSrc();
	// }

	public static void deleteFolder(File folder) {
		File[] files = folder.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}
		folder.delete();
	}

}
