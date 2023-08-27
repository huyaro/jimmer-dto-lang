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

public class DtoExplicitPropImpl extends ASTWrapperPsiElement implements DtoExplicitProp {

  public DtoExplicitPropImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DtoVisitor visitor) {
    visitor.visitExplicitProp(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DtoVisitor) accept((DtoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DtoAliasGroup getAliasGroup() {
    return findChildByClass(DtoAliasGroup.class);
  }

  @Override
  @Nullable
  public DtoAllScalars getAllScalars() {
    return findChildByClass(DtoAllScalars.class);
  }

  @Override
  @Nullable
  public DtoNegativeProp getNegativeProp() {
    return findChildByClass(DtoNegativeProp.class);
  }

  @Override
  @Nullable
  public DtoPositiveProp getPositiveProp() {
    return findChildByClass(DtoPositiveProp.class);
  }

  @Override
  @Nullable
  public DtoUserProp getUserProp() {
    return findChildByClass(DtoUserProp.class);
  }

}
