// This is a generated file. Not intended for manual editing.
package dev.huyaro.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DtoUserProp extends PsiElement {

  @NotNull
  DtoIdentifier getIdentifier();

  @NotNull
  List<DtoAnnotation> getAnnotationList();

  @NotNull
  DtoTypeRef getTypeRef();

}
