package dev.huyaro.lang.action

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectFileIndex
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.JavaPsiFacade
import dev.huyaro.lang.DtoUtil
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

/**
 * GeneratorAction
 *
 * @author huyaro
 * @date 2023-09-12
 */
class GeneratorAction : AnAction() {

    override fun actionPerformed(evt: AnActionEvent) {
        val project = evt.project
        if (project == null) {
            Messages.showInfoMessage("Please choose project!", "Usage Help")
            return
        }
        val files = evt.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY)
        if (files.isNullOrEmpty()) {
            Messages.showInfoMessage(project, "Please choose file first!", "Usage Help")
            return
        }
        val module = DtoUtil.getCurrentModule(project, files[0]);
        module?.let {
            val data = buildBindData(project, module, files)
            GeneratorUI(project, module, data).show()
        }
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT;
    }

    override fun update(evt: AnActionEvent) {
        var visible = false
        val files = evt.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY)
        if (!files.isNullOrEmpty() && files.any { f ->
                f.name.endsWith(Lang.JAVA.value) || f.name.endsWith(Lang.Kotlin.value)
            }
        ) {
            visible = true
        }
        evt.presentation.isVisible = visible
    }

    private fun buildBindData(project: Project, module: Module, files: Array<VirtualFile>): BindData {
        val sourceDir = DtoUtil.getSourceAndBuildDir(module).getFirst()
        val outputDir = Paths.get(sourceDir).resolveSibling(Lang.DTO.value)
        val entitiesData =
            files.map { fl ->
                val fileNoSuffix = fl.name
                    .replace(Lang.Kotlin.value, "")
                    .replace(Lang.JAVA.value, "")
                val clsName = Paths.get(fl.path).parent.toString()
                    .replace(sourceDir, "")
                    .substring(1) // remove prefix /
                    .replace(File.separator, ".") + ".$fileNoSuffix"
                // java 与 kotlin 通用的查找class的方式
                JavaPsiFacade.getInstance(project).findClass(clsName, module.moduleScope)
            }.filter { cls ->
                cls?.isInterface ?: false
                        && cls?.annotations?.any { ann ->
                    ann.qualifiedName == "org.babyfish.jimmer.sql.Entity"
                } ?: false
            }.associate { cls ->
                cls?.qualifiedName!! to joinOutputPath(cls.qualifiedName!!, outputDir)
            }
        val moduleRootDir = DtoUtil.getModuleRootDir(module)
        val classes = entitiesData.keys.joinToString("\n")
        return BindData(moduleRootDir, outputDir.toString(), classes, entitiesData)
    }

    private fun joinOutputPath(clsName: String, outputDir: Path): String {
        val clsPath = Paths.get(clsName.replace(".", File.separator))
        return outputDir.resolve("$clsPath.${Lang.DTO.value}").toString()
    }
}
