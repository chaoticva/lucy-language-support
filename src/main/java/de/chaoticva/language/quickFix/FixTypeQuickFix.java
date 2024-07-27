package de.chaoticva.language.quickFix;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import de.chaoticva.language.psi.LucyElementFactory;
import de.chaoticva.language.psi.LucyVarDef;
import org.jetbrains.annotations.NotNull;

public class FixTypeQuickFix implements LocalQuickFix {
    @Override
    public @IntentionFamilyName @NotNull String getFamilyName() {
        return "Fix type";
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
        PsiElement element = descriptor.getPsiElement();
        if (element instanceof LucyVarDef varDef) {
            var type = varDef.getType();
            varDef.addBefore(LucyElementFactory.createType(project, varDef.getExpr().getType()), type);
            type.delete();
        }
    }
}
