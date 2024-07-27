package de.chaoticva.language;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.lang.documentation.DocumentationMarkup;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import de.chaoticva.language.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

final class LucyAnnotator implements Annotator {
    public static final String LUCY_PREFIX_STR = "$";

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof LucyExpr literalExpression) annotateExpr(holder, literalExpression);
        if (element instanceof LucyType type) annotateIdentifier(holder, type);
        if (element instanceof LucyDefCall defCall) annotateDefCall(holder, defCall);
        if (element instanceof LucyParameter parameter) annotateParameter(holder, parameter);
        if (element instanceof LucyInstanceDef instance) annotateInstance(holder, instance);
    }

    private void annotateInstance(AnnotationHolder holder, LucyInstanceDef instance) {
        holder.newAnnotation(HighlightSeverity.TEXT_ATTRIBUTES, "class instance '%s'".formatted(instance.getIdentifier().getText())).range(instance.getIdentifier()).textAttributes(LucySyntaxHighlighter.CLASS).create();
    }

    private void annotateParameter(AnnotationHolder holder, LucyParameter parameter) {
        StringBuilder sb = new StringBuilder();

        sb.append(DocumentationMarkup.DEFINITION_START);
        sb.append("Function parameter '%s'".formatted(parameter.getName()));
        sb.append(DocumentationMarkup.DEFINITION_END);

        sb.append(DocumentationMarkup.SECTIONS_START);
        addKeyValueSection("Name:", parameter.getName(), sb);
        addKeyValueSection("Type:", parameter.getType().getText(), sb);
        sb.append(DocumentationMarkup.SECTIONS_END);

        holder.newAnnotation(HighlightSeverity.TEXT_ATTRIBUTES, "").range(parameter.getIdentifier()).tooltip(sb.toString()).textAttributes(LucySyntaxHighlighter.PARAMETER).create();
    }

    private void annotateDefCall(AnnotationHolder holder, LucyDefCall defCall) {
        LucyDefDef defDef = LucyUtil.getDeclaration(defCall);
        if (defDef != null) {
            StringBuilder builder = new StringBuilder();
            for (LucyParameter parameter : defDef.getParameterList()) {
                builder.append("%s %s, ".formatted(parameter.getType().getText(), parameter.getName()));
            }
            String parameters = builder.isEmpty() ? "" : builder.substring(0, builder.length() - 2);

            StringBuilder sb = new StringBuilder();
            sb.append(DocumentationMarkup.DEFINITION_START);
            sb.append("def %s %s(%s)".formatted(defCall.getName(), defDef.getType().getText(), parameters));
            sb.append(DocumentationMarkup.DEFINITION_END);
            sb.append(DocumentationMarkup.SECTIONS_START);
            addKeyValueSection("File", defCall.getContainingFile().getName(), sb);
            sb.append(DocumentationMarkup.SECTIONS_END);

            holder.newAnnotation(HighlightSeverity.TEXT_ATTRIBUTES, "").tooltip(sb.toString()).range(defCall.getIdentifier()).textAttributes(LucySyntaxHighlighter.FUNCTION_CALL).create();
        }
    }

    private void annotateIdentifier(AnnotationHolder holder, LucyType type) {
        if (List.of(
                "str",
                "num",
                "bool",
                "char",
                "void",
                "auto"
        ).contains(type.getText())) {
            holder.newAnnotation(HighlightSeverity.TEXT_ATTRIBUTES, "Type %s".formatted(type.getText())).textAttributes(LucySyntaxHighlighter.TYPE).create();
        } else {
            holder.newAnnotation(HighlightSeverity.TEXT_ATTRIBUTES, "Type %s".formatted(type.getText())).textAttributes(LucySyntaxHighlighter.CLASS).create();
        }
    }

    private void annotateExpr(AnnotationHolder holder, LucyExpr literalExpression) {
        String value = literalExpression.getValue() != null ? literalExpression.getValue() : null;
        if (value == null || !value.startsWith(LUCY_PREFIX_STR)) return;

        String name = value.substring(LUCY_PREFIX_STR.length()).split("[^A-Za-z\\d_]")[0];

        TextRange prefixRange = TextRange.from(literalExpression.getTextRange().getStartOffset() + 1, LUCY_PREFIX_STR.length());
        TextRange keyRange = new TextRange(prefixRange.getEndOffset(), prefixRange.getEndOffset() + name.length());

        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(prefixRange).textAttributes(DefaultLanguageHighlighterColors.KEYWORD).create();


        List<LucyVarDef> properties = LucyUtil.findVarDefs(literalExpression.getProject(), name);
        if (properties.isEmpty()) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Unresolved identifier").range(keyRange).highlightType(ProblemHighlightType.ERROR).create();
        } else {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(keyRange).textAttributes(LucySyntaxHighlighter.IDENTIFIER).create();
        }
    }

    private void addKeyValueSection(String key, String value, StringBuilder sb) {
        sb.append(DocumentationMarkup.SECTION_HEADER_START);
        sb.append(key);
        sb.append(DocumentationMarkup.SECTION_SEPARATOR);
        sb.append("<p>");
        sb.append(value);
        sb.append(DocumentationMarkup.SECTION_END);
    }
}