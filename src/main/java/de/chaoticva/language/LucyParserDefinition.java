package de.chaoticva.language;

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
import de.chaoticva.language.parser.LucyParser;
import de.chaoticva.language.psi.LucyFile;
import de.chaoticva.language.psi.LucyTokenSets;
import de.chaoticva.language.psi.LucyTypes;
import org.jetbrains.annotations.NotNull;

final class LucyParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE = new IFileElementType(LucyLanguage.INSTANCE);

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new LucyLexerAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new LucyParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return LucyTokenSets.STRINGS;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        return LucyTypes.Factory.createElement(node);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new LucyFile(viewProvider);
    }
}
