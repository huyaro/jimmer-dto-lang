// This is a generated file. Not intended for manual editing.
package dev.huyaro.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DtoAnnotationSingleValue extends PsiElement {

  @Nullable
  DtoBooleanLiteral getBooleanLiteral();

  @Nullable
  DtoAnnotation getAnnotation();

  @Nullable
  DtoNestedAnnotation getNestedAnnotation();

  @Nullable
  DtoQualifiedName getQualifiedName();

  @Nullable
  PsiElement getNumber();

  @Nullable
  PsiElement getString();

  @Nullable
  PsiElement getStringLiteral();

}
