package de.chaoticva.language;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import de.chaoticva.language.psi.LucyExpr;
import de.chaoticva.language.psi.LucyVarDef;
import org.jetbrains.annotations.NotNull;

import java.util.List;

final class LucyAnnotator implements Annotator {
    public static final String LUCY_PREFIX_STR = "$";

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof LucyExpr literalExpression)) return;

        String value = literalExpression.getValue() != null ? literalExpression.getValue() : null;
        if (value == null || !value.startsWith(LUCY_PREFIX_STR)) return;

        String name = value.substring(LUCY_PREFIX_STR.length()).split("[^A-Za-z\\d_]")[0];

        TextRange prefixRange = TextRange.from(element.getTextRange().getStartOffset() + 1, LUCY_PREFIX_STR.length());
        TextRange keyRange = new TextRange(prefixRange.getEndOffset(), prefixRange.getEndOffset() + name.length());

        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(prefixRange).textAttributes(DefaultLanguageHighlighterColors.KEYWORD).create();


        List<LucyVarDef> properties = LucyUtil.findVarDefs(element.getProject(), name);
        if (properties.isEmpty()) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Unresolved identifier").range(keyRange).highlightType(ProblemHighlightType.ERROR)
//          .withFix(new SimpleCreatePropertyQuickFix(key))
                    .create();
        } else {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(keyRange).textAttributes(LucySyntaxHighlighter.IDENTIFIER).create();
        }
    }
}