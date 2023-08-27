package dev.huyaro.lang;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.PlatformIcons;
import com.intellij.util.containers.ContainerUtil;
import dev.huyaro.lang.psi.*;
import dev.huyaro.lang.psi.impl.DtoDtoTypeImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DtoStructureViewElement implements StructureViewTreeElement, SortableTreeElement {

    private final NavigatablePsiElement myElement;

    public DtoStructureViewElement(NavigatablePsiElement element) {
        this.myElement = element;
    }

    @Override
    public Object getValue() {
        return myElement;
    }

    @Override
    public void navigate(boolean requestFocus) {
        myElement.navigate(requestFocus);
    }

    @Override
    public boolean canNavigate() {
        return myElement.canNavigate();
    }

    @Override
    public boolean canNavigateToSource() {
        return myElement.canNavigateToSource();
    }

    @NotNull
    @Override
    public ItemPresentation getPresentation() {
        return new ItemPresentation() {
            @Override
            public @NlsSafe @Nullable String getPresentableText() {
                if (myElement instanceof DtoDtoType type) {
                    return type.getQualifiedNameList().get(0).getText();
                } else if (myElement instanceof DtoExplicitProp prop) {
                    PsiElement actualProp = prop.getFirstChild();
                    if (actualProp instanceof DtoAllScalars propAllScalars) {
                        return "#" + propAllScalars.getIdentifier().getId().getText();
                    } else if (actualProp instanceof DtoAliasGroup propAliasGroup) {
                        return propAliasGroup.getAliasPattern().getText();
                    } else if (actualProp instanceof DtoNegativeProp propNegative) {
                        return propNegative.getText();
                    } else if (actualProp instanceof DtoPositiveProp propPositive) {
                        return propPositive.getIdentifierList().get(0).getText();
                    } else if (actualProp instanceof DtoUserProp propUser) {
                        return "@" + propUser.getIdentifier().getId().getText();
                    }
                }
                return "";
            }

            @Override
            public @Nullable Icon getIcon(boolean unused) {
                if (myElement instanceof DtoDtoType type) {
                    return type.getModifierList().isEmpty() ? DtoIcons.VIEW_TYPE : DtoIcons.INPUT_TYPE;
                }
                return PlatformIcons.FIELD_ICON;
            }
        };
    }


    @Override
    public @NotNull String getAlphaSortKey() {
        String name = myElement.getName();
        return name == null ? "" : name;
    }

    @Override
    public TreeElement @NotNull [] getChildren() {
        if (myElement instanceof DtoFile file) {
            List<DtoDtoType> dtoTypes = PsiTreeUtil.getChildrenOfTypeAsList(file, DtoDtoType.class);
            List<TreeElement> treeElements = new ArrayList<>(dtoTypes.size());
            for (DtoDtoType type : dtoTypes) {
                treeElements.add(new DtoStructureViewElement((DtoDtoTypeImpl) type));
            }
            return treeElements.toArray(new TreeElement[0]);
        } else if (myElement instanceof DtoDtoTypeImpl type) {
            List<DtoExplicitProp> propList =
                    PsiTreeUtil.getChildrenOfTypeAsList(type.getDtoBody(), DtoExplicitProp.class);
            return ContainerUtil.map2Array(propList, TreeElement.class,
                    prop -> new DtoStructureViewElement((NavigatablePsiElement) prop));
        }
        return EMPTY_ARRAY;
    }
}
