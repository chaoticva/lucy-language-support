// This is a generated file. Not intended for manual editing.
package de.chaoticva.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LucyDefCall extends PsiElement {

  @NotNull
  List<LucyArgument> getArgumentList();

  @NotNull
  LucyIdentifier getIdentifier();

  String getName();

  //WARNING: getType(...) is skipped
  //matching getType(LucyDefCall, ...)
  //methods are not found in LucyPsiImplUtil

}
