package dev.huyaro.lang;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class DtoFileType extends LanguageFileType {

    public static final DtoFileType INSTANCE = new DtoFileType();

    private DtoFileType() {
        super(DtoLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Dto File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Dto language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "dto";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return DtoIcons.FILE;
    }

}
