package dev.huyaro.lang.highlighter;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import dev.huyaro.lang.DtoLexerAdapter;
import dev.huyaro.lang.psi.DtoTokenSets;
import dev.huyaro.lang.psi.DtoTypes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static dev.huyaro.lang.highlighter.DtoSyntaxHighlighterColors.*;

public class DtoSyntaxHighlighter extends SyntaxHighlighterBase {
    private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<>();

    static {
        fillMap(ATTRIBUTES, LINE_COMMENT, DtoTokenSets.LINE_COMMENT);
        fillMap(ATTRIBUTES, BLOCK_COMMENT, DtoTokenSets.BLOCK_COMMENT);
        fillMap(ATTRIBUTES, PARENTHESES, DtoTypes.LPAREN, DtoTypes.RPAREN);
        fillMap(ATTRIBUTES, BRACKETS, DtoTypes.RBRACK, DtoTypes.RBRACK);
        fillMap(ATTRIBUTES, BRACES, DtoTypes.LBRACE, DtoTypes.RBRACE);
        fillMap(ATTRIBUTES, BAD_CHARACTER, TokenType.BAD_CHARACTER);
        fillMap(ATTRIBUTES, IDENTIFIER, DtoTypes.IDENTIFIER);
        fillMap(ATTRIBUTES, DOT, DtoTypes.DOT);
        fillMap(ATTRIBUTES, COLON, DtoTypes.COLON);
        fillMap(ATTRIBUTES, SEMICOLON, DtoTypes.SEMICOLON);
        fillMap(ATTRIBUTES, COMMA, DtoTypes.COMMA);

        fillMap(ATTRIBUTES, KEYWORD, DtoTypes.KEY_AS, DtoTypes.IMPORT, DtoTypes.ABSTRACT, DtoTypes.INPUT);
        fillMap(ATTRIBUTES, TYPE_REFERENCE, DtoTypes.RIGHT_ARROW, DtoTypes.OPTNULL, DtoTypes.STAR);
        fillMap(ATTRIBUTES, BUILTIN_FUNCTION, DtoTypes.MINUS, DtoTypes.HASH, DtoTypes.CARET, DtoTypes.DOLOR);

        fillMap(ATTRIBUTES, METADATA, DtoTypes.AT);
        fillMap(ATTRIBUTES, STRING, DtoTypes.STRINGLITERAL);
        fillMap(ATTRIBUTES, NUMBER, DtoTypes.NUMBER);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new DtoLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        return pack(ATTRIBUTES.get(tokenType));
    }

}
