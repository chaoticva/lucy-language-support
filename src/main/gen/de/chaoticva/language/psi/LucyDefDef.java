// This is a generated file. Not intended for manual editing.
package de.chaoticva.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LucyDefDef extends PsiElement {

  @NotNull
  LucyIdentifier getIdentifier();

  @NotNull
  List<LucyParameter> getParameterList();

  @NotNull
  LucyScope getScope();

  @NotNull
  LucyType getType();

  String getName();

  PsiElement getNameIdentifier();

}
