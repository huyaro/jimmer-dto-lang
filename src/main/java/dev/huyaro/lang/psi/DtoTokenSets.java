package dev.huyaro.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public interface DtoTokenSets {

    TokenSet IDENTIFIERS = TokenSet.create(DtoTypes.ID);

    IElementType LINE_COMMENT = new DtoTokenType("Line_Comment");
    IElementType BLOCK_COMMENT = new DtoTokenType("Block_Comment");
}
