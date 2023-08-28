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

public class DtoAnnotationArrayValueImpl extends ASTWrapperPsiElement implements DtoAnnotationArrayValue {

  public DtoAnnotationArrayValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DtoVisitor visitor) {
    visitor.visitAnnotationArrayValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DtoVisitor) accept((DtoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<DtoAnnotationSingleValue> getAnnotationSingleValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DtoAnnotationSingleValue.class);
  }

}