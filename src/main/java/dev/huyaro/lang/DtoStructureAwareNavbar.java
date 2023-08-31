package dev.huyaro.lang;

import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension;
import com.intellij.lang.Language;
import com.intellij.util.PlatformIcons;
import dev.huyaro.lang.psi.DtoDtoType;
import dev.huyaro.lang.psi.DtoExplicitProp;
import dev.huyaro.lang.psi.DtoFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class DtoStructureAwareNavbar extends StructureAwareNavBarModelExtension {
    @NotNull
    @Override
    protected Language getLanguage() {
        return DtoLanguage.INSTANCE;
    }

    @Override
    public @Nullable String getPresentableText(Object object) {
        if (object instanceof DtoFile file) {
            return file.getName();
        }
        if (object instanceof DtoDtoType type) {
            return type.getIdentifierList().get(0).getText();
        }
        if (object instanceof DtoExplicitProp explicitProp) {
            return DtoUtil.findActualPropName(explicitProp);
        }
        return "";
    }

    @Override
    @Nullable
    public Icon getIcon(Object object) {
        if (object instanceof DtoDtoType type) {
            return type.getModifierList().isEmpty() ? DtoIcons.VIEW_TYPE : DtoIcons.INPUT_TYPE;
        }
        if (object instanceof DtoExplicitProp) {
            return PlatformIcons.FIELD_ICON;
        }

        return null;
    }

}
