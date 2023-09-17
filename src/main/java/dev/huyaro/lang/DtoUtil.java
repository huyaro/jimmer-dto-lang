package dev.huyaro.lang;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import dev.huyaro.lang.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaSourceRootType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;


public final class DtoUtil {

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

    /**
     * 获取source 和 build 目录
     *
     * @param module 当前模块
     * @return source and build dir
     */
    public static @NotNull Pair<String, String> getSourceAndBuildDir(Module module) {
        // 获取当前模块下被标记为源码的所有目录(包含源码目录及asp/ksp编译后的目录)
        List<VirtualFile> sourceRoots = ModuleRootManager.getInstance(module)
                .getSourceRoots(JavaSourceRootType.SOURCE);
        // 只可能有source与build目录, 再多了也无法区分
        if (sourceRoots.size() == 2) {
            final List<String> mainDirSpec = List.of("src/main/java", "src/main/kotlin");
            VirtualFile sourceRoot = null;
            for (String spec : mainDirSpec) {
                for (VirtualFile root : sourceRoots) {
                    if (Paths.get(root.getPath()).endsWith(spec)) {
                        sourceRoot = root;
                        break;
                    }
                }
            }
            if (null != sourceRoot) {
                String sourceDir = sourceRoot.getPath();
                VirtualFile virtualBuildDir = sourceRoots.stream()
                        .filter(p -> !p.getPath().equals(sourceDir))
                        .findFirst()
                        .orElse(null);
                assert virtualBuildDir != null;
                return new Pair<>(sourceDir, virtualBuildDir.getPath());
            }
        }
        return null;
    }

    /**
     * 获取模块根目录
     *
     * @param module 模块
     * @return 根目录
     */
    public static String getModuleRootDir(Module module) {
        String sourceDir = Objects.requireNonNull(getSourceAndBuildDir(module)).getFirst();
        final List<String> mainDirSpec = List.of("src/main/java", "src/main/kotlin");
        return mainDirSpec.stream()
                .filter(p -> Paths.get(sourceDir).endsWith(p))
                .map(p -> sourceDir.replace(p, ""))
                .findFirst()
                .orElse("");
    }

    /**
     * 跳转文件
     */
    public static void navigateTo(Project project, Path filePath) {
        if(Files.exists(filePath)) {
            VirtualFile classFile = LocalFileSystem.getInstance().findFileByPath(filePath.toAbsolutePath().toString());
            assert classFile != null;
            OpenFileDescriptor openFileDescriptor = new OpenFileDescriptor(project, classFile);
            FileEditorManager.getInstance(project).openEditor(openFileDescriptor, true);
        }
    }
}
