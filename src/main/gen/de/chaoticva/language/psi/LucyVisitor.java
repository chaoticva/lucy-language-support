// This is a generated file. Not intended for manual editing.
package de.chaoticva.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class LucyVisitor extends PsiElementVisitor {

  public void visitArgument(@NotNull LucyArgument o) {
    visitPsiElement(o);
  }

  public void visitDefCall(@NotNull LucyDefCall o) {
    visitPsiElement(o);
  }

  public void visitDefDef(@NotNull LucyDefDef o) {
    visitPsiElement(o);
  }

  public void visitExpr(@NotNull LucyExpr o) {
    visitPsiElement(o);
  }

  public void visitFactor(@NotNull LucyFactor o) {
    visitPsiElement(o);
  }

  public void visitOperator(@NotNull LucyOperator o) {
    visitPsiElement(o);
  }

  public void visitParameter(@NotNull LucyParameter o) {
    visitPsiElement(o);
  }

  public void visitReassign(@NotNull LucyReassign o) {
    visitPsiElement(o);
  }

  public void visitScope(@NotNull LucyScope o) {
    visitPsiElement(o);
  }

  public void visitType(@NotNull LucyType o) {
    visitPsiElement(o);
  }

  public void visitVarDef(@NotNull LucyVarDef o) {
    visitNamedElement(o);
  }

  public void visitNamedElement(@NotNull LucyNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
