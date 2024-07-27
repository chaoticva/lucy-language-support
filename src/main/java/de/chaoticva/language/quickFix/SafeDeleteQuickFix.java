package de.chaoticva.language.quickFix;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import de.chaoticva.language.psi.LucyParameter;
import de.chaoticva.language.psi.LucyVarDef;
import org.jetbrains.annotations.NotNull;

public class SafeDeleteQuickFix implements LocalQuickFix {
    @Override
    public @IntentionFamilyName @NotNull String getFamilyName() {
        return "Safe delete";
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
        PsiElement element = descriptor.getPsiElement();
        if (element instanceof LucyVarDef varDef) {
            varDef.getNextSibling().delete();
            varDef.delete();
        }
        if (element instanceof LucyParameter param) {
            while(param.getPrevSibling() instanceof PsiWhiteSpace) {
                param.getPrevSibling().delete();
            }
            param.getPrevSibling().delete();
            param.delete();
        }
    }
}
