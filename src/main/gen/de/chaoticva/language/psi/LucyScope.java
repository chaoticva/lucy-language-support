// This is a generated file. Not intended for manual editing.
package de.chaoticva.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LucyScope extends PsiElement {

  @NotNull
  List<LucyDefCall> getDefCallList();

  @NotNull
  List<LucyIfDef> getIfDefList();

  @NotNull
  List<LucyInstanceDef> getInstanceDefList();

  @NotNull
  List<LucyReassign> getReassignList();

  @NotNull
  List<LucyVarDef> getVarDefList();

}
