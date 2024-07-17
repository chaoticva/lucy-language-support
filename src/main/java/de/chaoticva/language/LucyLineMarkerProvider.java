package de.chaoticva.language;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import de.chaoticva.language.psi.LucyExpr;
import de.chaoticva.language.psi.LucyVarDef;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

import static de.chaoticva.language.LucyAnnotator.LUCY_PREFIX_STR;

public class LucyLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        if (!(element.getParent() instanceof LucyExpr literalExpression)) return;

        String value = literalExpression.getValue() != null ? literalExpression.getValue() : null;
        if (value == null || !value.startsWith(LUCY_PREFIX_STR)) return;

        Project project = element.getProject();
        String name = value.substring(LUCY_PREFIX_STR.length()).split("[^A-Za-z\\d_]")[0];

        final List<LucyVarDef> varDefs = LucyUtil.findVarDefs(project, name);
        if (!varDefs.isEmpty()) {
            NavigationGutterIconBuilder<PsiElement> builder = NavigationGutterIconBuilder.create(LucyIcons.FILE).setTargets(varDefs).setTooltipText("Navigate to declaration");
            result.add(builder.createLineMarkerInfo(element));
        }
    }
}
