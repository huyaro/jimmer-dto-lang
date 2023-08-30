// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package dev.huyaro.lang.psi;

import com.intellij.psi.tree.TokenSet;

public interface DtoTokenSets {

    TokenSet IDENTIFIERS = TokenSet.create(DtoTypes.ID);

    TokenSet COMMENTS = TokenSet.create(DtoTypes.COMMENT);

}
