package dev.huyaro.lang.action

/**
 * ui data
 *
 * @author huyaro
 * @date 2023-09-12
 */
data class BindData(
    var moduleRootDir: String = "",
    var outputDir: String = "",
    var classes: String = "",
    var clsToFile: Map<String, String>,
    var writeMode: WriteMode = WriteMode.Skipping
)

enum class WriteMode {
    Overwrite, Skipping
}

enum class Lang(val value: String) {
    JAVA(".java"), Kotlin(".kt"), DTO("dto")
}