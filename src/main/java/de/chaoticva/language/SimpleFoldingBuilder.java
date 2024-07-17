package de.chaoticva.language;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementWalkingVisitor;
import com.intellij.psi.util.PsiLiteralUtil;
import com.intellij.util.containers.ContainerUtil;
import de.chaoticva.language.psi.LucyExpr;
import de.chaoticva.language.psi.LucyVarDef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static de.chaoticva.language.LucyAnnotator.LUCY_PREFIX_STR;

final class SimpleFoldingBuilder extends FoldingBuilderEx implements DumbAware {
    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        FoldingGroup group = FoldingGroup.newGroup(LUCY_PREFIX_STR);
        List<FoldingDescriptor> descriptors = new ArrayList<>();

        root.accept(new PsiRecursiveElementWalkingVisitor() {

            @Override
            public void visitElement(@NotNull PsiElement element) {
                super.visitElement(element);
                if (!(element instanceof LucyExpr literalExpression)) return;

                String value = literalExpression.getValue() != null ? literalExpression.getValue() : null;
                if (value != null && value.startsWith(LUCY_PREFIX_STR)) {
                    Project project = literalExpression.getProject();
                    String name = value.substring(LUCY_PREFIX_STR.length()).split("[^A-Za-z\\d_]")[0];
                    LucyVarDef varDef = ContainerUtil.getOnlyItem(LucyUtil.findVarDefs(project, name));
                    if (varDef != null) {
                        descriptors.add(new FoldingDescriptor(literalExpression.getNode(), new TextRange(literalExpression.getTextRange().getStartOffset() + 1, literalExpression.getTextRange().getEndOffset() - 1), group, Collections.singleton(varDef)));
                    }
                }
            }
        });

        return descriptors.toArray(FoldingDescriptor.EMPTY_ARRAY);
    }

    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        if (node.getPsi() instanceof LucyExpr literalExpression) {
            String value = literalExpression.getValue() != null ? literalExpression.getValue() : null;
            if (value == null) return null;

            String name = value.substring(LUCY_PREFIX_STR.length()).split("[^A-Za-z\\d_]")[0];

            LucyVarDef varDef = ContainerUtil.getOnlyItem(LucyUtil.findVarDefs(literalExpression.getProject(), name));
            if (varDef == null) return StringUtil.THREE_DOTS;

            String varValue = varDef.getValue();
            if (varValue == null) return StringUtil.THREE_DOTS;

            return varValue;
        }

        return null;
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return true;
    }
}