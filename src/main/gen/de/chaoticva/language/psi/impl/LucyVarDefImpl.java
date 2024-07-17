// This is a generated file. Not intended for manual editing.
package de.chaoticva.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static de.chaoticva.language.psi.LucyTypes.*;
import de.chaoticva.language.psi.*;

public class LucyVarDefImpl extends LucyNamedElementImpl implements LucyVarDef {

  public LucyVarDefImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LucyVisitor visitor) {
    visitor.visitVarDef(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LucyVisitor) accept((LucyVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public LucyExpr getExpr() {
    return findNotNullChildByClass(LucyExpr.class);
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

  @Override
  public String getValue() {
    return LucyPsiImplUtil.getValue(this);
  }

  @Override
  public PsiElement setName(String newName) {
    return LucyPsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return LucyPsiImplUtil.getNameIdentifier(this);
  }

}
