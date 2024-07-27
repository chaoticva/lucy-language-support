package de.chaoticva.language;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import de.chaoticva.language.psi.*;
import de.chaoticva.language.quickFix.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class LucyInspection extends LocalInspectionTool {
    @Override
    public @NotNull PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        return new PsiElementVisitor() {
            @Override
            public void visitElement(@NotNull PsiElement element) {
                super.visitElement(element);

                Project project = element.getProject();
                if (element instanceof LucyVarDef varDef) {
                    if (!LucyUtil.isUsed(varDef)) {
                        holder.registerProblem(varDef, "'%s' is never used".formatted(varDef.getName()), ProblemHighlightType.LIKE_UNUSED_SYMBOL, new SafeDeleteQuickFix());
                    }
                    if (!Objects.equals(varDef.getType().getText(), varDef.getExpr().getType())) {
                        holder.registerProblem(varDef, "type mismatch '%s' for '%s'".formatted(varDef.getExpr().getType(), varDef.getType().getText()), ProblemHighlightType.GENERIC_ERROR, new FixTypeQuickFix());
                    }
                    if (!LucyUtil.isReassigned(varDef) && !varDef.isConst()) {
                        holder.registerProblem(varDef, "'%s' can be made const".formatted(varDef.getName()), ProblemHighlightType.WEAK_WARNING, new MakeConstQuickFix());
                    }
                }
                if (element instanceof LucyReassign reassign) {
                    LucyVarDef varDef = LucyUtil.getDeclaration(reassign);
                    if (varDef == null) {
                        holder.registerProblem(reassign, "Invalid identifier '%s'".formatted(reassign.getName()), ProblemHighlightType.GENERIC_ERROR);
                    }
                    if (!Objects.equals(varDef.getType().getText(), reassign.getExpr().getType())) {
                        holder.registerProblem(reassign, "type mismatch '%s' for '%s'".formatted(reassign.getExpr().getType(), varDef.getType().getText()), ProblemHighlightType.GENERIC_ERROR, new FixTypeQuickFix());
                    }
                    if (varDef.isConst()) {
                        holder.registerProblem(reassign, "'%s' cannot be modified".formatted(varDef.getName()), ProblemHighlightType.GENERIC_ERROR, new MakeVarQuickFix());
                    }
                }
                if (element instanceof LucyDefCall defCall) {
                    LucyDefDef defDef = LucyUtil.getDeclaration(defCall);
                    if (defCall.getName().equals("print") || defCall.getName().equals("exit")) {
                    }else {
                        if (defDef == null) {
                            holder.registerProblem(defCall, "Undefined Function '%s'".formatted(defCall.getName()), ProblemHighlightType.GENERIC_ERROR, new CreateDefQuickFix());
                        } else {
                            if (defDef.getParameterList().size() < defCall.getArgumentList().size()) {
                                holder.registerProblem(defCall, "Too many arguments", ProblemHighlightType.GENERIC_ERROR);
                            }
                            if (defDef.getParameterList().size() > defCall.getArgumentList().size()) {
                                holder.registerProblem(defCall, "Missing arguments", ProblemHighlightType.GENERIC_ERROR);
                            }
                            for (int i = 0; i < defDef.getParameterList().size(); i++) {
                                if (!defCall.getArgumentList().isEmpty()) {
                                    LucyArgument argument = defCall.getArgumentList().get(i);
                                    String paramType = defDef.getParameterList().get(i).getType().getText();
                                    String argType = argument.getExpr().getType();
                                    if (!paramType.equals("auto")) {
                                        if (!paramType.equals(argType)) {
                                            holder.registerProblem(argument, "Type mismatch", ProblemHighlightType.GENERIC_ERROR);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (element instanceof LucyParameter param) {
                    if (!LucyUtil.isUsed(param)) {
                        holder.registerProblem(param, "'%s' is never used".formatted(param.getName()), ProblemHighlightType.LIKE_UNUSED_SYMBOL, new SafeDeleteQuickFix());
                    }
                }
            }
        };
    }
}
