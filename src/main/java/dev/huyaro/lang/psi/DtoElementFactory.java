package dev.huyaro.lang.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import dev.huyaro.lang.DtoFileType;

public class DtoElementFactory {

  public static DtoExplicitProp createProperty(Project project, String name) {
    final DtoFile file = createFile(project, name);
    return (DtoExplicitProp) file.getFirstChild();
  }

  public static DtoFile createFile(Project project, String text) {
    String name = "dummy.dto";
    return (DtoFile) PsiFileFactory.getInstance(project).createFileFromText(name, DtoFileType.INSTANCE, text);
  }

  public static DtoExplicitProp createProperty(Project project, String name, String value) {
    final DtoFile file = createFile(project, name + " = " + value);
    return (DtoExplicitProp) file.getFirstChild();
  }

  public static PsiElement createCRLF(Project project) {
    final DtoFile file = createFile(project, "\n");
    return file.getFirstChild();
  }

}
