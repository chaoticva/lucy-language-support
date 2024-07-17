// This is a generated file. Not intended for manual editing.
package de.chaoticva.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LucyIfDef extends PsiElement {

  @NotNull
  List<LucyCondition> getConditionList();

  @Nullable
  LucyIfDef getIfDef();

  @NotNull
  List<LucyScope> getScopeList();

}
