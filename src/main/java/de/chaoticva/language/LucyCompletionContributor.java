package de.chaoticva.language;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import de.chaoticva.language.psi.LucyDefDef;
import de.chaoticva.language.psi.LucyFile;
import de.chaoticva.language.psi.LucyParameter;
import de.chaoticva.language.psi.LucyVarDef;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LucyCompletionContributor extends CompletionContributor {
    LucyCompletionContributor() {
        extend(CompletionType.SMART, PlatformPatterns.psiElement().afterLeaf("="), new CompletionProvider<>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters params, @NotNull ProcessingContext ctx, @NotNull CompletionResultSet result) {
                Project project = params.getOriginalFile().getProject();

                result.addElement(LookupElementBuilder.create("true"));
                result.addElement(LookupElementBuilder.create("false"));

                for (LucyVarDef varDef : LucyUtil.findVarDefs(project)) {
                    result.addElement(LookupElementBuilder.create(Objects.requireNonNull(varDef.getName())).withIcon(AllIcons.Nodes.Variable).withTypeText(varDef.getType().getText(), true));
                }

                for (LucyDefDef defDef : LucyUtil.findDefDefs((LucyFile) params.getOriginalFile())) {
                    StringBuilder parameters = new StringBuilder("(");
                    for (LucyParameter parameter : defDef.getParameterList()) {
                        parameters.append("%s: %s, ".formatted(parameter.getName(), parameter.getType().getText()));
                    }

                    String parameterText = parameters.length() != 1 ? parameters.substring(0, parameters.length() - 2) : "(";
                    parameterText += ")";

                    LookupElementBuilder builder = LookupElementBuilder
                            .create(defDef.getName())
                            .withIcon(AllIcons.Nodes.Method)
                            .withTypeText(defDef.getType().getText(), true)
                            .withTailText(parameterText, true)
                            .withInsertHandler((context, item) -> {
                                context.getDocument().insertString(context.getTailOffset(), "()");
                                if (defDef.getParameterList().isEmpty())
                                    context.getEditor().getCaretModel().moveToOffset(context.getTailOffset());
                                else
                                    context.getEditor().getCaretModel().moveToOffset(context.getTailOffset() - 1);
                            });

                    result.addElement(builder);
                }
            }
        });
    }
}
