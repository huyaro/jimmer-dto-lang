// This is a generated file. Not intended for manual editing.
package dev.huyaro.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static dev.huyaro.lang.psi.DtoTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import dev.huyaro.lang.psi.*;

public class DtoAnnotationSingleValueImpl extends ASTWrapperPsiElement implements DtoAnnotationSingleValue {

  public DtoAnnotationSingleValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DtoVisitor visitor) {
    visitor.visitAnnotationSingleValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DtoVisitor) accept((DtoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DtoBooleanLiteral getBooleanLiteral() {
    return findChildByClass(DtoBooleanLiteral.class);
  }

  @Override
  @Nullable
  public DtoAnnotation getAnnotation() {
    return findChildByClass(DtoAnnotation.class);
  }

  @Override
  @Nullable
  public DtoNestedAnnotation getNestedAnnotation() {
    return findChildByClass(DtoNestedAnnotation.class);
  }

  @Override
  @Nullable
  public DtoQualifiedName getQualifiedName() {
    return findChildByClass(DtoQualifiedName.class);
  }

  @Override
  @Nullable
  public PsiElement getNumber() {
    return findChildByType(NUMBER);
  }

  @Override
  @Nullable
  public PsiElement getString() {
    return findChildByType(STRING);
  }

  @Override
  @Nullable
  public PsiElement getStringLiteral() {
    return findChildByType(STRINGLITERAL);
  }

}
