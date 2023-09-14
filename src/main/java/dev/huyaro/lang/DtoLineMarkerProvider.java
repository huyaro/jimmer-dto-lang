package dev.huyaro.lang;

import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import dev.huyaro.lang.psi.DtoDtoType;
import dev.huyaro.lang.psi.DtoIdentifier;
import dev.huyaro.lang.psi.DtoModifier;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static com.intellij.openapi.module.ModuleUtilCore.findModuleForFile;

public class DtoLineMarkerProvider implements LineMarkerProvider {

    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
        if (element instanceof DtoDtoType dtoType) {
            List<DtoModifier> modifierList = dtoType.getModifierList();
            List<DtoIdentifier> nameList = dtoType.getIdentifierList();
            if (!nameList.isEmpty()) {
                // 获取编译后的dto class的名称
                PsiElement idElement = nameList.get(0).getId();
                // 判断当前图标对应的dtoType是否为抽象
                boolean isAbsDtoType = modifierList.stream()
                        .map(PsiElement::getText)
                        .anyMatch(txt -> Objects.equals(txt.strip(), "abstract"));
                String tooltip = isAbsDtoType
                        ? "Abstract Dto don't generate any files"
                        : "Navigate to class [" + idElement.getText() + ".class]";
                // abstract 不用跳转
                GutterIconNavigationHandler<PsiElement> navHandler = isAbsDtoType ? null : navigateToClass(element);
                Icon typeIcon = modifierList.isEmpty() ? DtoIcons.VIEW_TYPE : DtoIcons.INPUT_TYPE;

                return new LineMarkerInfo<>(idElement, idElement.getTextRange(), typeIcon, t -> tooltip,
                        navHandler, GutterIconRenderer.Alignment.LEFT, () -> "");
            }
        }
        return null;
    }

    private GutterIconNavigationHandler<PsiElement> navigateToClass(@NotNull PsiElement element) {
        return (evt, ele) -> {
            Project project = element.getProject();
            VirtualFile dtoFile = element.getContainingFile().getVirtualFile();
            Module module = findModuleForFile(dtoFile, project);

            if (module != null) {
                Pair<String, String> sourceAndBuildDir = DtoUtil.getSourceAndBuildDir(module);
                if (sourceAndBuildDir != null) {
                    final String sourceRoot = sourceAndBuildDir.getFirst();
                    final String buildRoot = sourceAndBuildDir.getSecond();
                    final String dtoDirName = "dto";

                    Path sourceRootPath = Paths.get(sourceRoot);
                    String suffixName = sourceRootPath.resolveSibling("java").equals(sourceRootPath)
                            ? ".java" : ".kt";

                    // 将 src/main/java 替换成 src/main/dto 去构建dto生成的源码文件相对路径
                    String sourceFile = sourceRootPath.resolveSibling(dtoDirName)
                            .relativize(Paths.get(dtoFile.getPath()))
                            .resolveSibling(dtoDirName)
                            .resolve(ele.getText() + suffixName)
                            .toString();
                    // 构建完整的源码文件路径
                    File fullSourceFile = Paths.get(buildRoot, sourceFile).toFile();
                    if (fullSourceFile.exists()) {
                        VirtualFile classFile = LocalFileSystem.getInstance().findFileByPath(fullSourceFile.getAbsolutePath());
                        assert classFile != null;
                        OpenFileDescriptor openFileDescriptor = new OpenFileDescriptor(project, classFile);
                        FileEditorManager.getInstance(project).openEditor(openFileDescriptor, true);
                    }
                }
            }
        };
    }
}
