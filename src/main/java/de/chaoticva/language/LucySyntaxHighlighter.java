package de.chaoticva.language;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import de.chaoticva.language.psi.LucyTypes;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class LucySyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey OPERATOR = createTextAttributesKey("LUCY_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("LUCY_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey KEYWORD = createTextAttributesKey("LUCY_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING = createTextAttributesKey("LUCY_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("LUCY_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey BRACES = createTextAttributesKey("LUCY_BRACES", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey PARENTHESES = createTextAttributesKey("LUCY_PARENS", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey COMMA = createTextAttributesKey("LUCY_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey FUNCTION_CALL = createTextAttributesKey("LUCY_DEF_CALL", DefaultLanguageHighlighterColors.FUNCTION_CALL);
    public static final TextAttributesKey FUNCTION_DECLARATION = createTextAttributesKey("LUCY_DEF_DECL", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey PARAMETER = createTextAttributesKey("LUCY_PARAMETER", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey SEMICOLON = createTextAttributesKey("LUCY_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("LUCY_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey TYPE = createTextAttributesKey("LUCY_TYPE", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey CLASS = createTextAttributesKey("LUCY_CLASS", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("LUCY_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new LucyLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (Objects.equals(LucyTypes.SEMI, tokenType)) return new TextAttributesKey[]{SEMICOLON};
        if (List.of(
                LucyTypes.OPEN_ANGLE,
                LucyTypes.CLOSE_ANGLE,
                LucyTypes.EQUAL,
                LucyTypes.NOT_EQUAL,
                LucyTypes.LTE,
                LucyTypes.GTE,
                LucyTypes.PLUS,
                LucyTypes.MINUS,
                LucyTypes.ASTERISK,
                LucyTypes.SLASH,
                LucyTypes.AND,
                LucyTypes.OR
        ).contains(tokenType)) return new TextAttributesKey[]{OPERATOR};
        if (Objects.equals(LucyTypes.COMMENT, tokenType)) return new TextAttributesKey[]{COMMENT};
        if (Objects.equals(LucyTypes.DEF_DEF, tokenType)) return new TextAttributesKey[]{FUNCTION_DECLARATION};
        if (Objects.equals(LucyTypes.DEF_CALL, tokenType)) return new TextAttributesKey[]{FUNCTION_CALL};
        if (Objects.equals(LucyTypes.COMMA, tokenType)) return new TextAttributesKey[]{COMMA};
        if (List.of(LucyTypes.OPEN_PAREN, LucyTypes.CLOSE_PAREN).contains(tokenType)) return new TextAttributesKey[]{PARENTHESES};
        if (List.of(LucyTypes.OPEN_BRACE, LucyTypes.CLOSE_BRACE).contains(tokenType)) return new TextAttributesKey[]{BRACES};
        if (Objects.equals(LucyTypes.NUMBER, tokenType)) return new TextAttributesKey[]{NUMBER};
        if (Objects.equals(LucyTypes.STRING, tokenType)) return new TextAttributesKey[]{STRING};
        if (List.of(
                LucyTypes.VAR,
                LucyTypes.DEF,
                LucyTypes.IF,
                LucyTypes.ELSE,
                LucyTypes.CONST,
                LucyTypes.NEW
        ).contains(tokenType)) return new TextAttributesKey[]{KEYWORD};
        if (Objects.equals(LucyTypes.IDENTIFIER, tokenType)) return new TextAttributesKey[]{IDENTIFIER};
        if (Objects.equals(TokenType.BAD_CHARACTER, tokenType)) return new TextAttributesKey[]{BAD_CHARACTER};

        return new TextAttributesKey[0];
    }
}
