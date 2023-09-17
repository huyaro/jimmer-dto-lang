package dev.huyaro.lang.provider;

import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.ui.awt.RelativePoint;
import dev.huyaro.lang.DtoIcons;
import dev.huyaro.lang.DtoUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.psi.KtClass;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * java entity 跳转 dto 文件
 *
 * @author huyaro
 * @date 2023-09-16
 */
public class JvmLineMarketProvider extends RelatedItemLineMarkerProvider {
    public static final String ANN_ENTITY = "org.babyfish.jimmer.sql.Entity";

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {

        boolean isEntity = false;
        String qualifiedName = "";
        PsiElement identifier = element;

        Project project = element.getProject();
        VirtualFile file = element.getContainingFile().getVirtualFile();
        Module module = ModuleUtilCore.findModuleForFile(file, project);

        // java与kotlin分别判断是否为@entity
        if (element instanceof PsiClass cls && cls.isInterface()) {
            isEntity = Arrays.stream(cls.getAnnotations())
                    .anyMatch(ann -> Objects.equals(ann.getQualifiedName(), ANN_ENTITY));
            qualifiedName = cls.getQualifiedName();
            identifier = cls.getNameIdentifier();
        } else if (element instanceof KtClass ktCls && ktCls.isInterface()) {
            FqName fqName = ktCls.getFqName();
            identifier = ktCls.getNameIdentifier();
            if (null == fqName || fqName.toString().isBlank()) {
                return;
            }
            qualifiedName = fqName.toString();
            if (null != module) {
                PsiClass psiKtCls = JavaPsiFacade.getInstance(project)
                        .findClass(qualifiedName, module.getModuleScope());
                if (null != psiKtCls) {
                    isEntity = Arrays.stream(psiKtCls.getAnnotations())
                            .anyMatch(ann -> Objects.equals(ann.getQualifiedName(), ANN_ENTITY));
                }
            }
        }
        if (isEntity && null != identifier) {
            String sourceDir = DtoUtil.getSourceAndBuildDir(module).first;
            Path dtoFile = locateDtoFile(file, sourceDir);

            // 定义不同颜色的图标去区分当前entity对应的dto文件是否存在
            Icon iconFile = DtoIcons.FILE_NOT_FOUND;
            String toolTip = "Create dto file";
            if (Files.exists(dtoFile)) {
                iconFile = DtoIcons.FILE;
                toolTip = "Navigate dto file";
            }

            GutterIconNavigationHandler<PsiElement> navHandler = navigationHandler(project, dtoFile, qualifiedName);
            RelatedItemLineMarkerInfo<PsiElement> dtoLineMarker =
                    NavigationGutterIconBuilder.create(iconFile)
                            .setTarget(element)
                            .setTooltipText(toolTip)
                            .createLineMarkerInfo(identifier, navHandler);
            result.add(dtoLineMarker);
        }
    }

    /**
     * 根据java/kt源文件定位dto文件路径
     *
     * @param file      虚拟文件
     * @param sourceDir 源码目录
     * @return dto文件完整路径
     */
    private @NotNull Path locateDtoFile(VirtualFile file, String sourceDir) {
        String dtoSourceDir = Paths.get(sourceDir).resolveSibling("dto").toString();
        String dtoFileName = file.getName()
                .replace(".java", ".dto")
                .replace(".kt", ".dto");
        Path relatDtoPath = Paths.get(file.getPath()
                .replace(sourceDir, "")
                .substring(1)).resolveSibling(dtoFileName);
        return Paths.get(dtoSourceDir).resolve(relatDtoPath);
    }

    /**
     * dto文件存在时跳转, 不存在时弹出创建窗口
     *
     * @param project       project
     * @param dtoFile       dto文件路径
     * @param qualifiedName class 全限定名
     * @return 事件
     */
    private GutterIconNavigationHandler<PsiElement> navigationHandler(Project project, Path dtoFile,
                                                                      String qualifiedName) {
        return Files.exists(dtoFile)
                // 跳转到dto文件
                ? (evt, ele) -> DtoUtil.navigateTo(project, dtoFile)
                // 创建dto文件
                : (evt, ele) ->
                JBPopupFactory.getInstance()
                        .createConfirmation("Do You Want to Create a Dto File?", "Create", "Cancel",
                                () -> createDtoFile(project, dtoFile, qualifiedName, ele), 0)
                        .show(new RelativePoint(evt));

    }

    /**
     * 创建一个包含基本结构的dto文件
     *
     * @param project       project
     * @param dtoFile       dto file full path
     * @param qualifiedName class name
     * @param ele           element
     */
    private static void createDtoFile(Project project, Path dtoFile, String qualifiedName, PsiElement ele) {
        String dtoDir = dtoFile.getParent().toString();
        VirtualFile dtoRoot;
        try {
            dtoRoot = VfsUtil.createDirectories(dtoDir);
        } catch (IOException e) {
            throw new CreateDtoFieException("Create Directories [" + dtoDir + "] Failed!");
        }

        String[] namesArr = qualifiedName.split("\\.");
        String clsName = namesArr[namesArr.length - 1];
        String content = "/**\n" +
                " * From " + qualifiedName + "\n" +
                " */\n\n" +
                "input " + clsName + "Input {\n" +
                "    #allScalars(" + clsName + ")\n" +
                "}\n";

        // write file content
        WriteCommandAction.runWriteCommandAction(ele.getProject(), () -> {
            try {
                VirtualFile dtoVFile = dtoRoot.createChildData(ele.getProject(), dtoFile.getFileName().toString());
                dtoVFile.setBinaryContent(content.getBytes());
                dtoVFile.refresh(true, false);
                // Navigate after creating dto file
                DtoUtil.navigateTo(project, dtoFile);
            } catch (IOException e) {
                throw new CreateDtoFieException("Write file [" + dtoFile + "] Failed!");
            }
        });
    }

    private static class CreateDtoFieException extends RuntimeException {
        public CreateDtoFieException(String message) {
            super(message);
        }
    }
}
