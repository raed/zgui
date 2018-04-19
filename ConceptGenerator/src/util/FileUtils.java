package util;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class FileUtils {

	public static final String MODEL_PACKAGE = "concept.generated.model";
	public static final String BASEMODEL_PACKAGE = MODEL_PACKAGE + ".base";

	@CreateFolder
	public static final File GENERATION_TARGET_FOLDER = new File("../ConceptTarget/");
	@CreateFolder
	public static final File SRCMAIN_FOLDER = new File(GENERATION_TARGET_FOLDER, "src/main/");
	@CreateFolder
	public static final File SRCMAINJAVACONCEPT_FOLDER = new File(SRCMAIN_FOLDER, "java/concept/");

	@CreateFolder
	public static final File MODEL_FOLDER = new File(SRCMAINJAVACONCEPT_FOLDER, "generated/model/");
	@CreateFolder
	public static final File BASEMODEL_FOLDER = new File(MODEL_FOLDER, "base/");
//
//	public static final File PREDEFINED_FOLDER = new File("../ConceptPredefined/");
//	public static final File PREDEFINED_SRC_FOLDER = new File(PREDEFINED_FOLDER, "src/main/java/concept/predefined/");
//	public static final File PREDEFINED_TARGET_FOLDER = new File(SRCMAINJAVACONCEPT_FOLDER, "predefined/");
//
//	public static final File PREDEFINED_RESOURCES_FOLDER = new File(PREDEFINED_FOLDER, "src/main/resources/");
//	public static final File RESOURCES_TARGET_FOLDER = new File(SRCMAIN_FOLDER, "resources/");
//
//	@CopyToTarget("pom.xml")
//	public static final File POMFILE = new File(PREDEFINED_FOLDER, "pom.xml");
//
	public static void generateAllFolders() throws IllegalArgumentException, IllegalAccessException {
		for (Field f : FileUtils.class.getFields()) {
			if (!f.isAnnotationPresent(CreateFolder.class)) {
				continue;
			}
			if (f.getType().equals(File.class)) {
				File file = (File) f.get(new Object());
				file.mkdirs();
			}
		}
	}
//
//	public static void copyPredefinedFiles() throws IllegalArgumentException, IllegalAccessException, IOException {
//
//		for (Field f : Settings.class.getFields()) {
//			if (!f.isAnnotationPresent(CopyToTarget.class)) {
//				continue;
//			}
//			File toCopyFile = (File) f.get(new Object());
//			File targetFolder = new File(GENERATION_TARGET_FOLDER, f.getAnnotation(CopyToTarget.class).value());
//
//			Files.copy(toCopyFile.toPath(), targetFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
//		}
//	}
//
//	public static void copyPredefinedSrc() throws IOException {
//		copyFolder(PREDEFINED_SRC_FOLDER, PREDEFINED_TARGET_FOLDER);
//		copyFolder(PREDEFINED_RESOURCES_FOLDER, RESOURCES_TARGET_FOLDER);
//	}
//
//	public static void copyFolder(File sourceFolder, File destinationFolder) throws IOException {
//		if (sourceFolder.isDirectory()) {
//			if (!destinationFolder.exists()) {
//				destinationFolder.mkdirs();
//			}
//
//			String files[] = sourceFolder.list();
//
//			for (String file : files) {
//				File srcFile = new File(sourceFolder, file);
//				File destFile = new File(destinationFolder, file);
//				copyFolder(srcFile, destFile);
//			}
//		} else {
//			Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
//		}
//	}
//}
//
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface CreateFolder {

}
//
//@Target(ElementType.FIELD)
//@Retention(RetentionPolicy.RUNTIME)
//@interface CopyToTarget {
//
//	String value();
//
}
