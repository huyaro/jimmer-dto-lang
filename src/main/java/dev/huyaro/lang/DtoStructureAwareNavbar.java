package dev.huyaro.lang;

import com.intellij.icons.AllIcons;
import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension;
import com.intellij.lang.Language;
import com.intellij.psi.PsiElement;
import dev.huyaro.lang.psi.*;
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
            return type.getQualifiedNameList().get(0).getText();
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
            return AllIcons.Nodes.Property;
        }

        return null;
    }

}
