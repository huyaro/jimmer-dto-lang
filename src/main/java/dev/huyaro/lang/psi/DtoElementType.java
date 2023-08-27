package dev.huyaro.lang.psi;

import com.intellij.psi.tree.IElementType;
import dev.huyaro.lang.DtoLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class DtoElementType extends IElementType {

    public DtoElementType(@NotNull @NonNls String debugName) {
        super(debugName, DtoLanguage.INSTANCE);
    }

}
