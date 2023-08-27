package dev.huyaro.lang;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import dev.huyaro.lang.parser.DtoParser;
import dev.huyaro.lang.psi.DtoFile;
import dev.huyaro.lang.psi.DtoTokenSets;
import dev.huyaro.lang.psi.DtoTypes;
import org.jetbrains.annotations.NotNull;

public class DtoParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(DtoLanguage.INSTANCE);
    public static final TokenSet STRING_LITERALS = TokenSet.create(DtoTypes.STRING, DtoTypes.QUALIFIED_NAME);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new DtoLexerAdapter();
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return DtoTokenSets.COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return STRING_LITERALS;
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new DtoParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new DtoFile(viewProvider);
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return DtoTypes.Factory.createElement(node);
    }

}
