// This is a generated file. Not intended for manual editing.
package de.chaoticva.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LucyExpr extends PsiElement {

  @NotNull
  List<LucyFactor> getFactorList();

  @NotNull
  List<LucyOperator> getOperatorList();

  String getValue();

  String getType();

}
