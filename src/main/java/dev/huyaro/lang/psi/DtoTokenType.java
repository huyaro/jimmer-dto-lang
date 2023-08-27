package dev.huyaro.lang.psi;

import com.intellij.psi.tree.IElementType;
import dev.huyaro.lang.DtoLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class DtoTokenType extends IElementType {

  public DtoTokenType(@NotNull @NonNls String debugName) {
    super(debugName, DtoLanguage.INSTANCE);
  }

  @Override
  public String toString() {
    return "DtoTokenType." + super.toString();
  }

}
