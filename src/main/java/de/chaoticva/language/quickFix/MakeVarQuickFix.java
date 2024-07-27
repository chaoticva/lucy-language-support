package de.chaoticva.language.quickFix;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import de.chaoticva.language.LucyUtil;
import de.chaoticva.language.psi.LucyReassign;
import de.chaoticva.language.psi.LucyVarDef;
import org.jetbrains.annotations.NotNull;

public class MakeVarQuickFix implements LocalQuickFix {
    @Override
    public @IntentionFamilyName @NotNull String getFamilyName() {
        return "Make mutable";
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
        PsiElement element = descriptor.getPsiElement();
        if (element instanceof LucyReassign reassign) {
            LucyVarDef varDef = LucyUtil.getDeclaration(reassign);
            if (varDef == null) return;

            varDef.getFirstChild().delete();
        }
    }
}
