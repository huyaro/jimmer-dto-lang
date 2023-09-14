package dev.huyaro.lang.action

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.util.Computable
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import java.awt.BorderLayout
import java.awt.Dimension
import java.nio.file.Files
import java.nio.file.Paths
import javax.swing.JComponent
import javax.swing.JPanel
import kotlin.io.path.exists
import kotlin.io.path.name


/**
 * UI
 *
 * @author huyaro
 * @date 2023-09-12
 */
class GeneratorUI(
    private val project: Project?,
    module: Module?,
    private val data: BindData
) :
    DialogWrapper(project, null, true, IdeModalityType.MODELESS, false) {

    init {
        super.init()
        title = "Jimmer Dto Generator [${module?.name}]"
    }

    private lateinit var txtLog: Cell<JBTextArea>
    private lateinit var genPanel: DialogPanel

    private fun initPanel(): DialogPanel {
        return panel {
            buttonsGroup {
                row("FileMode: ") {
                    WriteMode.values().forEach {
                        radioButton(it.name, it)
                    }
                }
            }.bind(data::writeMode)
            row("Output Path: ") {
                textField()
                    .bindText(data::outputDir)
                    .horizontalAlign(HorizontalAlign.FILL)
            }.layout(RowLayout.PARENT_GRID)
            row("Classes: ") {
                txtLog = textArea()
                    .rows(5)
                    .bindText(data::classes)
                    .horizontalAlign(HorizontalAlign.FILL)
                txtLog.component.isEditable = false
            }.layout(RowLayout.PARENT_GRID)
            row {
                button("Cancel") {
                    super.close(0, true)
                }.horizontalAlign(HorizontalAlign.RIGHT)
                    .resizableColumn()
                button("Generate") {
                    generateDto()
                }
            }.layout(RowLayout.INDEPENDENT)
                .topGap(TopGap.MEDIUM)
        }
    }

    override fun createCenterPanel(): JComponent {
        val panel = JPanel(BorderLayout())
        panel.minimumSize = Dimension(500, 200)
        panel.preferredSize = Dimension(800, 230)
        genPanel = initPanel()
        panel.add(genPanel)
        return panel;
    }

    /**
     * 生成dto文件
     */
    private fun generateDto() {
        // apply()后界面的变更项才会生效
        genPanel.apply()
        // 循环判断并创建文件
        data.clsToFile.forEach { (cls, file) ->
            val relativeFilePath = file.replace(data.moduleRootDir, "")
            if (data.writeMode == WriteMode.Skipping) {
                if (Paths.get(file).exists()) {
                    val log = "\n[Skipped] => $relativeFilePath"
                    txtLog.component.insert(log, txtLog.component.text.length)
                    return@forEach
                }
            } else {
                // override 模式直接判断删除文件
                Paths.get(file)
                    .takeIf { it.exists() }
                    .apply {
                        Files.delete(this)
                        val log = "\n[Deleted] => $relativeFilePath"
                        txtLog.component.insert(log, txtLog.component.text.length)
                    }
            }
            val dtoDir = Paths.get(file).parent.toString()
            val dtoRoot = VfsUtil.createDirectoryIfMissing(dtoDir)
            // write file content
            WriteCommandAction.runWriteCommandAction(project, Computable {
                dtoRoot
                    ?.createChildData(project, Paths.get(file).name)
                    ?.apply {
                        val simpleName = cls.split(".").last()
                        val content = "/**\n * From $cls\n */\n\n" +
                                "input ${simpleName}Input {\n" +
                                "\t#allScalars(${simpleName})\n}\n"
                        setBinaryContent(content.toByteArray())
                        val log = "\n[Generated] => $simpleName -> $relativeFilePath"
                        txtLog.component.insert(log, txtLog.component.text.length)
                    }
                    ?.refresh(true, false)
            })
        }
    }

}