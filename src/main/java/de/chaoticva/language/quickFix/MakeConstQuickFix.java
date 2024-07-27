package de.chaoticva.language.quickFix;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import de.chaoticva.language.LucyUtil;
import de.chaoticva.language.psi.LucyElementFactory;
import de.chaoticva.language.psi.LucyTypes;
import de.chaoticva.language.psi.LucyVarDef;
import org.jetbrains.annotations.NotNull;

public class MakeConstQuickFix implements LocalQuickFix {
    @Override
    public @IntentionFamilyName @NotNull String getFamilyName() {
        return "Insert const";
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
        PsiElement element = descriptor.getPsiElement();
        if (element instanceof LucyVarDef varDef) {
            PsiElement firstChild = varDef.getFirstChild();

            varDef.addBefore(createConstElement(project), firstChild);
            varDef.addAfter(LucyElementFactory.createWhitespace(project), varDef.getFirstChild());
        }
    }

    private PsiElement createConstElement(Project project) {
        return LucyElementFactory.createConstElement(project);
    }
}
