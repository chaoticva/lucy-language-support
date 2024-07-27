package de.chaoticva.language.quickFix;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import de.chaoticva.language.psi.*;
import org.jetbrains.annotations.NotNull;

public class CreateDefQuickFix implements LocalQuickFix {
    @Override
    public @IntentionFamilyName @NotNull String getFamilyName() {
        return "Implement method";
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
        PsiElement element = descriptor.getPsiElement();
        if (element instanceof LucyDefCall defCall) {
            LucyFile file = (LucyFile) defCall.getContainingFile();

            file.addAfter(LucyElementFactory.createNewLine(project), file);

            file.addAfter(LucyElementFactory.createDef(project, defCall), file);
        }
    }
}
