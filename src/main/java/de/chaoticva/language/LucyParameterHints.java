package de.chaoticva.language;

import com.intellij.codeInsight.hints.InlayInfo;
import com.intellij.codeInsight.hints.InlayParameterHintsProvider;
import com.intellij.psi.PsiElement;
import de.chaoticva.language.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class LucyParameterHints implements InlayParameterHintsProvider {
    @Override
    public @NotNull List<InlayInfo> getParameterHints(@NotNull PsiElement element) {
        if (element instanceof LucyDefCall defCall) return getDefCallHints(defCall);
        if (element instanceof LucyVarDef varDef) return getIdentifierHints(varDef);

        return Collections.emptyList();
    }

    private List<InlayInfo> getIdentifierHints(LucyVarDef el) {
        LucyVarDef varDef = LucyUtil.getDeclaration(el.getExpr());
        if (varDef == null) return Collections.emptyList();

        List<InlayInfo> hints = new ArrayList<>();

        hints.add(new InlayInfo(varDef.getType().getText(), el.getExpr().getTextRange().getStartOffset()));

        return hints;
    }

    private List<InlayInfo> getDefCallHints(LucyDefCall defCall) {
        LucyDefDef defDef = LucyUtil.findDefDefs(defCall.getProject(), defCall.getName());
        if (defDef == null) return Collections.emptyList();

        List<LucyArgument> arguments = defCall.getArgumentList();
        List<LucyParameter> parameters = defDef.getParameterList();

        List<InlayInfo> hints = new ArrayList<>();
        for (int i = 0; i < arguments.size(); i++) {
            if (i < parameters.size()) {
                hints.add(new InlayInfo(parameters.get(i).getName(), arguments.get(i).getTextRange().getStartOffset()));
            }
        }
        return hints;
    }

    @Override
    public @NotNull Set<String> getDefaultBlackList() {
        return Set.of();
    }
}
