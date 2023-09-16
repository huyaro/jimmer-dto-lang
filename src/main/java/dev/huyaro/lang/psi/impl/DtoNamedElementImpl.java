package dev.huyaro.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import dev.huyaro.lang.psi.DtoNamedElement;
import org.jetbrains.annotations.NotNull;

public abstract class DtoNamedElementImpl extends ASTWrapperPsiElement implements DtoNamedElement {

  public DtoNamedElementImpl(@NotNull ASTNode node) {
    super(node);
  }

}
