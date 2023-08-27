package dev.huyaro.lang;

import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.psi.PsiElement;
import dev.huyaro.lang.psi.DtoDtoType;
import dev.huyaro.lang.psi.DtoModifier;
import dev.huyaro.lang.psi.DtoQualifiedName;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;

public class DtoLineMarkerProvider implements LineMarkerProvider {

    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
        if (element instanceof DtoDtoType dtoType) {
            List<DtoModifier> modifierList = dtoType.getModifierList();
            List<DtoQualifiedName> nameList = dtoType.getQualifiedNameList();
            if (!nameList.isEmpty()) {
                // 获取到最深层次的叶子节点
                PsiElement idElement = nameList.get(0).getIdentifierList().get(0).getId();
                Icon typeIcon = modifierList.isEmpty() ? DtoIcons.VIEW_TYPE : DtoIcons.INPUT_TYPE;
                String tooltip = modifierList.isEmpty() ? "View Dto" : "Input Dto";
                return new LineMarkerInfo<>(idElement, idElement.getTextRange(), typeIcon,
                        t -> tooltip, null,
                        GutterIconRenderer.Alignment.LEFT, () -> "");
            }
        }
        return null;
    }
}
