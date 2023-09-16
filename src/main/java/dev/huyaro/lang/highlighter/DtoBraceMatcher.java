package dev.huyaro.lang.highlighter;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import dev.huyaro.lang.psi.DtoTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * ...description...
 *
 * @author huyaro
 * @date 2023-09-16
 */
public class DtoBraceMatcher implements PairedBraceMatcher {
    @Override
    public BracePair @NotNull [] getPairs() {
        return new BracePair[]{
                new BracePair(DtoTypes.LBRACE, DtoTypes.RBRACE, true),
                new BracePair(DtoTypes.LBRACK, DtoTypes.RBRACK, false),
                new BracePair(DtoTypes.LPAREN, DtoTypes.RPAREN, false),
                new BracePair(DtoTypes.LANGLE, DtoTypes.RANGLE, false)
        };
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
