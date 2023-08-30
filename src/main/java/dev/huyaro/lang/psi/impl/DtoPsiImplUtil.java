// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package dev.huyaro.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import dev.huyaro.lang.psi.DtoElementFactory;
import dev.huyaro.lang.psi.DtoExplicitProp;
import dev.huyaro.lang.psi.DtoTypes;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class DtoPsiImplUtil {

  public static String getKey(DtoExplicitProp element) {
    ASTNode keyNode = element.getNode().findChildByType(DtoTypes.ID);
    if (keyNode != null) {
      // IMPORTANT: Convert embedded escaped spaces to simple spaces
      return keyNode.getText().replace("\\\\ ", " ");
    } else {
      return null;
    }
  }

  public static String getValue(DtoExplicitProp element) {
    ASTNode valueNode = element.getNode().findChildByType(DtoTypes.STRINGLITERAL);
    if (valueNode != null) {
      return valueNode.getText();
    } else {
      return null;
    }
  }

  public static String getName(DtoExplicitProp element) {
    return getKey(element);
  }

  public static PsiElement setName(DtoExplicitProp element, String newName) {
    ASTNode keyNode = element.getNode().findChildByType(DtoTypes.ID);
    if (keyNode != null) {
      DtoExplicitProp property = DtoElementFactory.createProperty(element.getProject(), newName);
      ASTNode newKeyNode = property.getFirstChild().getNode();
      element.getNode().replaceChild(keyNode, newKeyNode);
    }
    return element;
  }

  public static PsiElement getNameIdentifier(DtoExplicitProp element) {
    ASTNode keyNode = element.getNode().findChildByType(DtoTypes.ID);
    if (keyNode != null) {
      return keyNode.getPsi();
    } else {
      return null;
    }
  }

  public static ItemPresentation getPresentation(final DtoExplicitProp element) {
    return new ItemPresentation() {
      @Nullable
      @Override
      public String getPresentableText() {
        return element.getText();
      }

      @Nullable
      @Override
      public String getLocationString() {
        PsiFile containingFile = element.getContainingFile();
        return containingFile == null ? null : containingFile.getName();
      }

      @Override
      public Icon getIcon(boolean unused) {
        return element.getIcon(0);
      }
    };
  }

}
