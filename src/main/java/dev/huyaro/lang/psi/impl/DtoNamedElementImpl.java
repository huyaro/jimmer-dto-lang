// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

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
