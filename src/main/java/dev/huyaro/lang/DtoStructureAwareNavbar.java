package dev.huyaro.lang;

import com.intellij.icons.AllIcons;
import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension;
import com.intellij.lang.Language;
import dev.huyaro.lang.psi.DtoFile;
import dev.huyaro.lang.psi.DtoExplicitProp;
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
        if (object instanceof DtoFile) {
            return ((DtoFile) object).getName();
        }
        if (object instanceof DtoExplicitProp) {
            return ((DtoExplicitProp) object).getText();
        }

        return null;
    }

    @Override
    @Nullable
    public Icon getIcon(Object object) {
        if (object instanceof DtoExplicitProp) {
            return AllIcons.Nodes.Property;
        }

        return null;
    }

}
