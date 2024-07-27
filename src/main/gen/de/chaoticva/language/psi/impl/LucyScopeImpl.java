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

public class LucyScopeImpl extends ASTWrapperPsiElement implements LucyScope {

  public LucyScopeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LucyVisitor visitor) {
    visitor.visitScope(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LucyVisitor) accept((LucyVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LucyDefCall> getDefCallList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LucyDefCall.class);
  }

  @Override
  @NotNull
  public List<LucyIfDef> getIfDefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LucyIfDef.class);
  }

  @Override
  @NotNull
  public List<LucyInstanceDef> getInstanceDefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LucyInstanceDef.class);
  }

  @Override
  @NotNull
  public List<LucyReassign> getReassignList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LucyReassign.class);
  }

  @Override
  @NotNull
  public List<LucyVarDef> getVarDefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LucyVarDef.class);
  }

}
