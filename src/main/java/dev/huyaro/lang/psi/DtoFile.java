package dev.huyaro.lang.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import dev.huyaro.lang.DtoFileType;
import dev.huyaro.lang.DtoLanguage;
import org.jetbrains.annotations.NotNull;

public class DtoFile extends PsiFileBase {

    public DtoFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, DtoLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return DtoFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Dto File";
    }

}
