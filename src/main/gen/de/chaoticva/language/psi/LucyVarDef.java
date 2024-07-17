// This is a generated file. Not intended for manual editing.
package de.chaoticva.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LucyVarDef extends LucyNamedElement {

  @NotNull
  LucyExpr getExpr();

  @Nullable
  LucyType getType();

  String getName();

  String getTypeText();

  String getValue();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

}
