package de.chaoticva.language.psi;

import com.intellij.psi.tree.TokenSet;

public interface LucyTokenSets {
    TokenSet IDENTIFIERS = TokenSet.create(LucyTypes.IDENTIFIER);
    TokenSet STRINGS = TokenSet.create(LucyTypes.STRING);
}
