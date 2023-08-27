package dev.huyaro.lang;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public interface DtoSyntaxHighlighterColors {
    TextAttributesKey LINE_COMMENT = createTextAttributesKey("DTO_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    TextAttributesKey BLOCK_COMMENT = createTextAttributesKey("DTO_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    TextAttributesKey KEYWORD = createTextAttributesKey("DTO_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    TextAttributesKey STRING = createTextAttributesKey("DTO_STRING", DefaultLanguageHighlighterColors.STRING);
    TextAttributesKey NUMBER = createTextAttributesKey("DTO_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    TextAttributesKey BRACKETS = createTextAttributesKey("DTO_BRACKET", DefaultLanguageHighlighterColors.BRACKETS);
    TextAttributesKey BRACES = createTextAttributesKey("DTO_BRACES", DefaultLanguageHighlighterColors.BRACES);
    TextAttributesKey PARENTHESES = createTextAttributesKey("DTO_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES);
    TextAttributesKey OPERATOR = createTextAttributesKey("DTO_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    TextAttributesKey IDENTIFIER = createTextAttributesKey("DTO_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    TextAttributesKey DOT = createTextAttributesKey("DTO_DOT", DefaultLanguageHighlighterColors.DOT);
    TextAttributesKey SEMICOLON = createTextAttributesKey("DTO_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
    TextAttributesKey COLON = createTextAttributesKey("DTO_COLON", HighlighterColors.TEXT);
    TextAttributesKey COMMA = createTextAttributesKey("DTO_COMMA", DefaultLanguageHighlighterColors.COMMA);
    TextAttributesKey BAD_CHARACTER = createTextAttributesKey("DTO_BAD_TOKEN", HighlighterColors.BAD_CHARACTER);
    TextAttributesKey TYPE_SPECIFICATION = createTextAttributesKey("DTO_TYPE_SPECIFICATION", DefaultLanguageHighlighterColors.CLASS_NAME);
    TextAttributesKey TYPE_REFERENCE = createTextAttributesKey("DTO_TYPE_REFERENCE", DefaultLanguageHighlighterColors.CLASS_REFERENCE);
    TextAttributesKey BUILTIN_TYPE_REFERENCE = createTextAttributesKey("DTO_BUILTIN_TYPE_REFERENCE", DefaultLanguageHighlighterColors.CLASS_REFERENCE);
    TextAttributesKey BUILTIN_FUNCTION = createTextAttributesKey("DTO_BUILTIN_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    TextAttributesKey EXPORTED_FUNCTION = createTextAttributesKey("DTO_EXPORTED_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    TextAttributesKey LOCAL_FUNCTION = createTextAttributesKey("DTO_LOCAL_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    TextAttributesKey PACKAGE_EXPORTED_INTERFACE = createTextAttributesKey("DTO_PACKAGE_EXPORTED_INTERFACE", DefaultLanguageHighlighterColors.INTERFACE_NAME);
    TextAttributesKey PACKAGE_EXPORTED_STRUCT = createTextAttributesKey("DTO_PACKAGE_EXPORTED_STRUCT", DefaultLanguageHighlighterColors.CLASS_NAME);
    TextAttributesKey PACKAGE_EXPORTED_CONSTANT = createTextAttributesKey("DTO_PACKAGE_EXPORTED_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT);
    TextAttributesKey PACKAGE_EXPORTED_VARIABLE = createTextAttributesKey("DTO_PACKAGE_EXPORTED_VARIABLE", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE);
    TextAttributesKey PACKAGE_LOCAL_INTERFACE = createTextAttributesKey("DTO_PACKAGE_LOCAL_INTERFACE", DefaultLanguageHighlighterColors.INTERFACE_NAME);
    TextAttributesKey PACKAGE_LOCAL_STRUCT = createTextAttributesKey("DTO_PACKAGE_LOCAL_STRUCT", DefaultLanguageHighlighterColors.CLASS_NAME);
    TextAttributesKey PACKAGE_LOCAL_CONSTANT = createTextAttributesKey("DTO_PACKAGE_LOCAL_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT);
    TextAttributesKey PACKAGE_LOCAL_VARIABLE = createTextAttributesKey("DTO_PACKAGE_LOCAL_VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
    TextAttributesKey STRUCT_EXPORTED_MEMBER = createTextAttributesKey("DTO_STRUCT_EXPORTED_MEMBER", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE);
    TextAttributesKey STRUCT_LOCAL_MEMBER = createTextAttributesKey("DTO_STRUCT_LOCAL_MEMBER", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
    TextAttributesKey METHOD_RECEIVER = createTextAttributesKey("DTO_METHOD_RECEIVER", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
    TextAttributesKey FUNCTION_PARAMETER = createTextAttributesKey("DTO_FUNCTION_PARAMETER", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
    TextAttributesKey LOCAL_CONSTANT = createTextAttributesKey("DTO_LOCAL_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT);
    TextAttributesKey LOCAL_VARIABLE = createTextAttributesKey("DTO_LOCAL_VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
    TextAttributesKey SCOPE_VARIABLE = createTextAttributesKey("DTO_SCOPE_VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
    TextAttributesKey LABEL = createTextAttributesKey("DTO_LABEL", DefaultLanguageHighlighterColors.LABEL);
}
