package dev.huyaro.lang;

import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import dev.huyaro.lang.psi.DtoDtoType;
import dev.huyaro.lang.psi.DtoIdentifier;
import dev.huyaro.lang.psi.DtoModifier;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
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
                        : "Jump to class [" + idElement.getText() + ".class]";
                // abstract 不用跳转
                GutterIconNavigationHandler<PsiElement> navHandler = isAbsDtoType ? null : handlerGotoClass(element);
                Icon typeIcon = modifierList.isEmpty() ? DtoIcons.VIEW_TYPE : DtoIcons.INPUT_TYPE;

                return new LineMarkerInfo<>(idElement, idElement.getTextRange(), typeIcon, t -> tooltip,
                        navHandler, GutterIconRenderer.Alignment.LEFT, ()->"");
            }
        }
        return null;
    }

    private GutterIconNavigationHandler<PsiElement> handlerGotoClass(@NotNull PsiElement element) {
        return (evt, ele) -> {
            Project project = element.getProject();
            VirtualFile dtoFile = element.getContainingFile().getVirtualFile();
            Module module = findModuleForFile(dtoFile, project);
            
            if (module != null) {
                // 获取当前模块下被标记为源码的所有目录(包含源码目录及asp/ksp编译后的目录)
                VirtualFile[] rootDirs = ModuleRootManager.getInstance(module).getContentRoots();
                final String dtoDirName = "dto";
                final String sourceMainDir = "src/main";
                // 以src/main做为标识过滤出源码目录
                Arrays.stream(rootDirs)
                        .filter(dir -> Paths.get(dir.getPath()).endsWith(sourceMainDir))
                        .findFirst()
                        .ifPresent(srcDir -> {
                            // 处理dto文件到编译后的class文件的仅包含包名的路径转换
                            String clsPath = Paths.get(srcDir.getPath(), dtoDirName)
                                    .relativize(Paths.get(dtoFile.getPath()))
                                    .resolveSibling(dtoDirName)
                                    .resolve(ele.getText() + ".class")
                                    .toString();
                            
                            // 去掉src/main子目录做为模块的项目根目录
                            String projectBaseDir = Paths.get(srcDir.getPath()).getParent().getParent().toString();
                            
                            // 替换可能存在的最终编译目录并检测文件是否存在, 存在则直接跳转
                            final List<String> targets =
                                    List.of("target/classes", "build/classes/java/main", "build/classes/kotlin/main");
                            targets.stream()
                                    .map(part -> Paths.get(projectBaseDir, part, clsPath))
                                    .filter(Files::exists)
                                    .findFirst()
                                    .ifPresent(p -> {
                                        File clsFile = p.toFile();
                                        VirtualFile classFile = LocalFileSystem.getInstance().findFileByPath(clsFile.getAbsolutePath());
                                        if (classFile != null) {
                                            OpenFileDescriptor openFileDescriptor = new OpenFileDescriptor(project, classFile);
                                            FileEditorManager.getInstance(project).openEditor(openFileDescriptor, true);
                                        }
                                    });
                        });
            }
        };
    }
}
