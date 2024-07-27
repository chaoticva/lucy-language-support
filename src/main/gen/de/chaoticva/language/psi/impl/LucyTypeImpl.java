// This is a generated file. Not intended for manual editing.
package de.chaoticva.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static de.chaoticva.language.psi.LucyTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import de.chaoticva.language.psi.*;

public class LucyTypeImpl extends ASTWrapperPsiElement implements LucyType {

  public LucyTypeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LucyVisitor visitor) {
    visitor.visitType(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LucyVisitor) accept((LucyVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public LucyIdentifier getIdentifier() {
    return findNotNullChildByClass(LucyIdentifier.class);
  }

}
