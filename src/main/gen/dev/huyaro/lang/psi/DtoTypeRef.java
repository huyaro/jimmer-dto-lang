// This is a generated file. Not intended for manual editing.
package dev.huyaro.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DtoTypeRef extends PsiElement {

  @Nullable
  DtoGenericArgument getGenericArgument();

  @Nullable
  DtoGenericModifier getGenericModifier();

  @NotNull
  DtoQualifiedName getQualifiedName();

}
