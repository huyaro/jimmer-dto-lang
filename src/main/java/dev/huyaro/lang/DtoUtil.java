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
import dev.huyaro.lang.psi.DtoFile;
import dev.huyaro.lang.psi.DtoExplicitProp;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DtoUtil {

  /**
   * Searches the entire project for Dto language files with instances of the Dto property with the given key.
   *
   * @param project current project
   * @param key     to check
   * @return matching properties
   */
  public static List<DtoExplicitProp> findProperties(Project project, String key) {
    List<DtoExplicitProp> result = new ArrayList<>();
    Collection<VirtualFile> virtualFiles =
            FileTypeIndex.getFiles(DtoFileType.INSTANCE, GlobalSearchScope.allScope(project));
    for (VirtualFile virtualFile : virtualFiles) {
      DtoFile dtoFile = (DtoFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (dtoFile != null) {
        DtoExplicitProp[] properties = PsiTreeUtil.getChildrenOfType(dtoFile, DtoExplicitProp.class);
        if (properties != null) {
          for (DtoExplicitProp property : properties) {
            if (key.equals(property.getText())) { //TODO 思考key与property之间的关系, 需要重写
              result.add(property);
            }
          }
        }
      }
    }
    return result;
  }

  public static List<DtoExplicitProp> findProperties(Project project) {
    List<DtoExplicitProp> result = new ArrayList<>();
    Collection<VirtualFile> virtualFiles =
            FileTypeIndex.getFiles(DtoFileType.INSTANCE, GlobalSearchScope.allScope(project));
    for (VirtualFile virtualFile : virtualFiles) {
      DtoFile dtoFile = (DtoFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (dtoFile != null) {
        DtoExplicitProp[] properties = PsiTreeUtil.getChildrenOfType(dtoFile, DtoExplicitProp.class);
        if (properties != null) {
          Collections.addAll(result, properties);
        }
      }
    }
    return result;
  }

  /**
   * Attempts to collect any comment elements above the Dto key/value pair.
   */
  public static @NotNull String findDocumentationComment(DtoExplicitProp property) {
    List<String> result = new LinkedList<>();
    PsiElement element = property.getPrevSibling();
    while (element instanceof PsiComment || element instanceof PsiWhiteSpace) {
      if (element instanceof PsiComment) {
        String commentText = element.getText().replaceFirst("[/* ]+", "");
        result.add(commentText);
      }
      element = element.getPrevSibling();
    }
    return StringUtil.join(Lists.reverse(result),"\n ");
  }

}
