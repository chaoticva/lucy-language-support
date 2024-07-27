package de.chaoticva.language.psi;

import com.intellij.psi.tree.TokenSet;

public interface LucyTokenSets {
    TokenSet IDENTIFIERS = TokenSet.create(LucyTypes.IDENTIFIER);
    TokenSet COMMENTS = TokenSet.create(LucyTypes.COMMENT);
    TokenSet STRINGS = TokenSet.create(LucyTypes.STRING);
    TokenSet ANY = TokenSet.create(
            LucyTypes.VAR,
            LucyTypes.IDENTIFIER,
            LucyTypes.ASSIGN,
            LucyTypes.NUMBER,
            LucyTypes.STRING,
            LucyTypes.BOOLEAN,
            LucyTypes.OPEN_PAREN,
            LucyTypes.CLOSE_PAREN,
            LucyTypes.PLUS,
            LucyTypes.MINUS,
            LucyTypes.ASTERISK,
            LucyTypes.SLASH,
            LucyTypes.EQUAL,
            LucyTypes.NOT_EQUAL,
            LucyTypes.LTE,
            LucyTypes.GTE,
            LucyTypes.OPEN_ANGLE,
            LucyTypes.CLOSE_ANGLE,
            LucyTypes.DEF,
            LucyTypes.COMMA,
            LucyTypes.OPEN_BRACE,
            LucyTypes.CLOSE_BRACE,
            LucyTypes.COMMENT,
            LucyTypes.SEMI,
            LucyTypes.IF,
            LucyTypes.OR,
            LucyTypes.AND,
            LucyTypes.ELSE
    );
}
