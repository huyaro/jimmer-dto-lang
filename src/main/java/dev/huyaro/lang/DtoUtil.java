// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package dev.huyaro.lang;

import com.google.common.collect.Lists;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import dev.huyaro.lang.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DtoUtil {

    public static String findActualPropName(DtoExplicitProp explicitProp) {
        PsiElement actualProp = explicitProp.getFirstChild();
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
        } else {
            return "";
        }
    }

}
