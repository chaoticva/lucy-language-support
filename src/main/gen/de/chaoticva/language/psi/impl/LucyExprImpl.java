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

public class LucyExprImpl extends ASTWrapperPsiElement implements LucyExpr {

  public LucyExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LucyVisitor visitor) {
    visitor.visitExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LucyVisitor) accept((LucyVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LucyFactor> getFactorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LucyFactor.class);
  }

  @Override
  @NotNull
  public List<LucyOperator> getOperatorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LucyOperator.class);
  }

  @Override
  public String getValue() {
    return LucyPsiImplUtil.getValue(this);
  }

}
