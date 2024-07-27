// This is a generated file. Not intended for manual editing.
package de.chaoticva.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import de.chaoticva.language.psi.impl.*;

public interface LucyTypes {

  IElementType ARGUMENT = new LucyElementType("ARGUMENT");
  IElementType CONDITION = new LucyElementType("CONDITION");
  IElementType DEF_CALL = new LucyElementType("DEF_CALL");
  IElementType DEF_DEF = new LucyElementType("DEF_DEF");
  IElementType EXPR = new LucyElementType("EXPR");
  IElementType FACTOR = new LucyElementType("FACTOR");
  IElementType IDENTIFIER = new LucyElementType("IDENTIFIER");
  IElementType IF_DEF = new LucyElementType("IF_DEF");
  IElementType INSTANCE_DEF = new LucyElementType("INSTANCE_DEF");
  IElementType OPERATOR = new LucyElementType("OPERATOR");
  IElementType PARAMETER = new LucyElementType("PARAMETER");
  IElementType REASSIGN = new LucyElementType("REASSIGN");
  IElementType SCOPE = new LucyElementType("SCOPE");
  IElementType TYPE = new LucyElementType("TYPE");
  IElementType VAR_DEF = new LucyElementType("VAR_DEF");

  IElementType AND = new LucyTokenType("AND");
  IElementType ASSIGN = new LucyTokenType("ASSIGN");
  IElementType ASTERISK = new LucyTokenType("ASTERISK");
  IElementType BOOLEAN = new LucyTokenType("BOOLEAN");
  IElementType CHAR = new LucyTokenType("CHAR");
  IElementType CLOSE_ANGLE = new LucyTokenType("CLOSE_ANGLE");
  IElementType CLOSE_BRACE = new LucyTokenType("CLOSE_BRACE");
  IElementType CLOSE_PAREN = new LucyTokenType("CLOSE_PAREN");
  IElementType COMMA = new LucyTokenType("COMMA");
  IElementType COMMENT = new LucyTokenType("COMMENT");
  IElementType CONST = new LucyTokenType("CONST");
  IElementType DEF = new LucyTokenType("DEF");
  IElementType ELSE = new LucyTokenType("ELSE");
  IElementType EQUAL = new LucyTokenType("EQUAL");
  IElementType GTE = new LucyTokenType("GTE");
  IElementType IDENTIFIER_ = new LucyTokenType("IDENTIFIER_");
  IElementType IF = new LucyTokenType("IF");
  IElementType LTE = new LucyTokenType("LTE");
  IElementType MINUS = new LucyTokenType("MINUS");
  IElementType NEW = new LucyTokenType("NEW");
  IElementType NOT_EQUAL = new LucyTokenType("NOT_EQUAL");
  IElementType NUMBER = new LucyTokenType("NUMBER");
  IElementType OPEN_ANGLE = new LucyTokenType("OPEN_ANGLE");
  IElementType OPEN_BRACE = new LucyTokenType("OPEN_BRACE");
  IElementType OPEN_PAREN = new LucyTokenType("OPEN_PAREN");
  IElementType OR = new LucyTokenType("OR");
  IElementType PLUS = new LucyTokenType("PLUS");
  IElementType SEMI = new LucyTokenType("SEMI");
  IElementType SLASH = new LucyTokenType("SLASH");
  IElementType STRING = new LucyTokenType("STRING");
  IElementType VAR = new LucyTokenType("VAR");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ARGUMENT) {
        return new LucyArgumentImpl(node);
      }
      else if (type == CONDITION) {
        return new LucyConditionImpl(node);
      }
      else if (type == DEF_CALL) {
        return new LucyDefCallImpl(node);
      }
      else if (type == DEF_DEF) {
        return new LucyDefDefImpl(node);
      }
      else if (type == EXPR) {
        return new LucyExprImpl(node);
      }
      else if (type == FACTOR) {
        return new LucyFactorImpl(node);
      }
      else if (type == IDENTIFIER) {
        return new LucyIdentifierImpl(node);
      }
      else if (type == IF_DEF) {
        return new LucyIfDefImpl(node);
      }
      else if (type == INSTANCE_DEF) {
        return new LucyInstanceDefImpl(node);
      }
      else if (type == OPERATOR) {
        return new LucyOperatorImpl(node);
      }
      else if (type == PARAMETER) {
        return new LucyParameterImpl(node);
      }
      else if (type == REASSIGN) {
        return new LucyReassignImpl(node);
      }
      else if (type == SCOPE) {
        return new LucyScopeImpl(node);
      }
      else if (type == TYPE) {
        return new LucyTypeImpl(node);
      }
      else if (type == VAR_DEF) {
        return new LucyVarDefImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
