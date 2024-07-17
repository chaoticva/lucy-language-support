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

public class LucyDefDefImpl extends ASTWrapperPsiElement implements LucyDefDef {

  public LucyDefDefImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LucyVisitor visitor) {
    visitor.visitDefDef(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LucyVisitor) accept((LucyVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LucyParameter> getParameterList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LucyParameter.class);
  }

  @Override
  @NotNull
  public LucyScope getScope() {
    return findNotNullChildByClass(LucyScope.class);
  }

  @Override
  @Nullable
  public LucyType getType() {
    return findChildByClass(LucyType.class);
  }

  @Override
  public String getName() {
    return LucyPsiImplUtil.getName(this);
  }

  @Override
  public String getTypeText() {
    return LucyPsiImplUtil.getTypeText(this);
  }

}
