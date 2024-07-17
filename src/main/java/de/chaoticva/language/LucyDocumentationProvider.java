package de.chaoticva.language;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.lang.documentation.DocumentationMarkup;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.presentation.java.SymbolPresentationUtil;
import com.intellij.psi.util.PsiTreeUtil;
import de.chaoticva.language.psi.LucyDefDef;
import de.chaoticva.language.psi.LucyParameter;
import de.chaoticva.language.psi.LucyVarDef;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

final class LucyDocumentationProvider extends AbstractDocumentationProvider {
    @Override
    public @Nullable @Nls String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
        final String file = SymbolPresentationUtil.getFilePathPresentation(element.getContainingFile());
        if (element instanceof LucyVarDef varDef) {
            final String name = varDef.getName();
            final String type = varDef.getTypeText();

            return renderVarDefDoc(name, type, file);
        }
        if (element instanceof LucyDefDef defDef) {
            final String name = defDef.getName();
            StringBuilder parameters = new StringBuilder("(");
            for (LucyParameter parameter : defDef.getParameterList()) {
                parameters.append("%s: %s, ".formatted(parameter.getName(), parameter.getTypeText()));
            }

            String parameterText = parameters.length() != 1 ? parameters.substring(0, parameters.length() - 2) : "(";
            parameterText += ")";
            final String type = defDef.getTypeText();
            return renderDefDefDoc(name, parameterText, type,  file);
        }

        return null;
    }

    @Override
    public @Nullable @Nls String generateHoverDoc(@NotNull PsiElement element, @Nullable PsiElement originalElement) {
        return generateDoc(element, originalElement);
    }

    @Override
    public @Nullable String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
        if (element instanceof LucyVarDef varDef) {
            final String name = varDef.getName();
            final String file = SymbolPresentationUtil.getFilePathPresentation(element.getContainingFile());
            return "\"" + name + "\" in " + file;
        }
        return null;
    }

    private String renderVarDefDoc(String name, String type, String file) {
        StringBuilder sb = new StringBuilder();
        sb.append(DocumentationMarkup.DEFINITION_START);
        sb.append("var %s: %s".formatted(name, type));
        sb.append(DocumentationMarkup.DEFINITION_END);
        sb.append(DocumentationMarkup.SECTIONS_START);
        addKeyValueSection("File:", file, sb);
        sb.append(DocumentationMarkup.SECTIONS_END);
        return sb.toString();
    }

    private String renderDefDefDoc(String name, String parameters, String type, String file) {
        StringBuilder sb = new StringBuilder();
        sb.append(DocumentationMarkup.DEFINITION_START);
        sb.append("def %s%s: %s".formatted(name, parameters, type));
        sb.append(DocumentationMarkup.DEFINITION_END);
        sb.append(DocumentationMarkup.SECTIONS_START);
        addKeyValueSection("File:", file, sb);
        sb.append(DocumentationMarkup.SECTIONS_END);
        return sb.toString();
    }

    private void addKeyValueSection(String key, String value, StringBuilder sb) {
        sb.append(DocumentationMarkup.SECTION_HEADER_START);
        sb.append(key);
        sb.append(DocumentationMarkup.SECTION_SEPARATOR);
        sb.append("<p>");
        sb.append(value);
        sb.append(DocumentationMarkup.SECTION_END);
    }

    @Override
    public @Nullable PsiElement getCustomDocumentationElement(@NotNull Editor editor, @NotNull PsiFile file, @Nullable PsiElement context, int targetOffset) {
        if (context == null) return super.getCustomDocumentationElement(editor, file, null, targetOffset);
        if (context.getLanguage() == LucyLanguage.INSTANCE) {
            PsiElement varDefProperty = PsiTreeUtil.getParentOfType(context, LucyVarDef.class);
            if (varDefProperty != null) return varDefProperty;
            PsiElement defDefProperty = PsiTreeUtil.getParentOfType(context, LucyDefDef.class);
            if (defDefProperty != null) return defDefProperty;
        }
        return super.getCustomDocumentationElement(editor, file, context, targetOffset);
    }
}
