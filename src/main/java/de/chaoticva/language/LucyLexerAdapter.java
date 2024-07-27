package de.chaoticva.language;

import com.intellij.lexer.FlexAdapter;
import com.intellij.psi.tree.IElementType;

public class LucyLexerAdapter extends FlexAdapter {
    private IElementType nextTokenType;

    public LucyLexerAdapter() {
        super(new LucyLexer(null));
    }
}