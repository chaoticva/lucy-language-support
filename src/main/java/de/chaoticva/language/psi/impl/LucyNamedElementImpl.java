package de.chaoticva.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class LucyNamedElementImpl extends ASTWrapperPsiElement {
    public LucyNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
