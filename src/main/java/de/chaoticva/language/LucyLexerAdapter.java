package de.chaoticva.language;

import com.intellij.lexer.FlexAdapter;

public class LucyLexerAdapter extends FlexAdapter {
    public LucyLexerAdapter() {
        super(new LucyLexer(null));
    }
}
