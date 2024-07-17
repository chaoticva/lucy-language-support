// This is a generated file. Not intended for manual editing.
package de.chaoticva.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static de.chaoticva.language.psi.LucyTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class LucyParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return lucyFile(b, l + 1);
  }

  /* ********************************************************** */
  // expr
  public static boolean argument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARGUMENT, "<argument>");
    r = expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // expr
  public static boolean condition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONDITION, "<condition>");
    r = expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER OPEN_PAREN (argument (COMMA argument)*)? CLOSE_PAREN
  public static boolean defCall(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defCall")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, OPEN_PAREN);
    r = r && defCall_2(b, l + 1);
    r = r && consumeToken(b, CLOSE_PAREN);
    exit_section_(b, m, DEF_CALL, r);
    return r;
  }

  // (argument (COMMA argument)*)?
  private static boolean defCall_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defCall_2")) return false;
    defCall_2_0(b, l + 1);
    return true;
  }

  // argument (COMMA argument)*
  private static boolean defCall_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defCall_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = argument(b, l + 1);
    r = r && defCall_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA argument)*
  private static boolean defCall_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defCall_2_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!defCall_2_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "defCall_2_0_1", c)) break;
    }
    return true;
  }

  // COMMA argument
  private static boolean defCall_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defCall_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && argument(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DEF IDENTIFIER OPEN_PAREN (parameter (COMMA parameter)*)? CLOSE_PAREN type? OPEN_BRACE scope CLOSE_BRACE
  public static boolean defDef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defDef")) return false;
    if (!nextTokenIs(b, DEF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DEF, IDENTIFIER, OPEN_PAREN);
    r = r && defDef_3(b, l + 1);
    r = r && consumeToken(b, CLOSE_PAREN);
    r = r && defDef_5(b, l + 1);
    r = r && consumeToken(b, OPEN_BRACE);
    r = r && scope(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACE);
    exit_section_(b, m, DEF_DEF, r);
    return r;
  }

  // (parameter (COMMA parameter)*)?
  private static boolean defDef_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defDef_3")) return false;
    defDef_3_0(b, l + 1);
    return true;
  }

  // parameter (COMMA parameter)*
  private static boolean defDef_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defDef_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parameter(b, l + 1);
    r = r && defDef_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA parameter)*
  private static boolean defDef_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defDef_3_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!defDef_3_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "defDef_3_0_1", c)) break;
    }
    return true;
  }

  // COMMA parameter
  private static boolean defDef_3_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defDef_3_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && parameter(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // type?
  private static boolean defDef_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defDef_5")) return false;
    type(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // factor ((operator factor)*)?
  public static boolean expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPR, "<expr>");
    r = factor(b, l + 1);
    r = r && expr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ((operator factor)*)?
  private static boolean expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_1")) return false;
    expr_1_0(b, l + 1);
    return true;
  }

  // (operator factor)*
  private static boolean expr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_1_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!expr_1_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expr_1_0", c)) break;
    }
    return true;
  }

  // operator factor
  private static boolean expr_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = operator(b, l + 1);
    r = r && factor(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NUMBER|STRING|BOOLEAN|OPEN_PAREN expr CLOSE_PAREN|defCall|IDENTIFIER
  public static boolean factor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "factor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FACTOR, "<factor>");
    r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, BOOLEAN);
    if (!r) r = factor_3(b, l + 1);
    if (!r) r = defCall(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // OPEN_PAREN expr CLOSE_PAREN
  private static boolean factor_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "factor_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_PAREN);
    r = r && expr(b, l + 1);
    r = r && consumeToken(b, CLOSE_PAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IF OPEN_PAREN condition (OR|AND condition)* CLOSE_PAREN OPEN_BRACE scope CLOSE_BRACE (ELSE (ifDef|OPEN_BRACE scope CLOSE_BRACE))?
  public static boolean ifDef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifDef")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IF, OPEN_PAREN);
    r = r && condition(b, l + 1);
    r = r && ifDef_3(b, l + 1);
    r = r && consumeTokens(b, 0, CLOSE_PAREN, OPEN_BRACE);
    r = r && scope(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACE);
    r = r && ifDef_8(b, l + 1);
    exit_section_(b, m, IF_DEF, r);
    return r;
  }

  // (OR|AND condition)*
  private static boolean ifDef_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifDef_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ifDef_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ifDef_3", c)) break;
    }
    return true;
  }

  // OR|AND condition
  private static boolean ifDef_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifDef_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OR);
    if (!r) r = ifDef_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // AND condition
  private static boolean ifDef_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifDef_3_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AND);
    r = r && condition(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (ELSE (ifDef|OPEN_BRACE scope CLOSE_BRACE))?
  private static boolean ifDef_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifDef_8")) return false;
    ifDef_8_0(b, l + 1);
    return true;
  }

  // ELSE (ifDef|OPEN_BRACE scope CLOSE_BRACE)
  private static boolean ifDef_8_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifDef_8_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE);
    r = r && ifDef_8_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ifDef|OPEN_BRACE scope CLOSE_BRACE
  private static boolean ifDef_8_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifDef_8_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ifDef(b, l + 1);
    if (!r) r = ifDef_8_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // OPEN_BRACE scope CLOSE_BRACE
  private static boolean ifDef_8_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifDef_8_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_BRACE);
    r = r && scope(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (COMMENT|(varDef SEMI)|defDef|(OPEN_BRACE scope CLOSE_BRACE)|(defCall SEMI)|(reassign SEMI)|ifDef)*
  static boolean lucyFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "lucyFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!lucyFile_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "lucyFile", c)) break;
    }
    return true;
  }

  // COMMENT|(varDef SEMI)|defDef|(OPEN_BRACE scope CLOSE_BRACE)|(defCall SEMI)|(reassign SEMI)|ifDef
  private static boolean lucyFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "lucyFile_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMENT);
    if (!r) r = lucyFile_0_1(b, l + 1);
    if (!r) r = defDef(b, l + 1);
    if (!r) r = lucyFile_0_3(b, l + 1);
    if (!r) r = lucyFile_0_4(b, l + 1);
    if (!r) r = lucyFile_0_5(b, l + 1);
    if (!r) r = ifDef(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // varDef SEMI
  private static boolean lucyFile_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "lucyFile_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = varDef(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  // OPEN_BRACE scope CLOSE_BRACE
  private static boolean lucyFile_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "lucyFile_0_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_BRACE);
    r = r && scope(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  // defCall SEMI
  private static boolean lucyFile_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "lucyFile_0_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = defCall(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  // reassign SEMI
  private static boolean lucyFile_0_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "lucyFile_0_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = reassign(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PLUS|MINUS|ASTERISK|SLASH|EQUAL|NOT_EQUAL|LTE|GTE|OPEN_ANGLE|CLOSE_ANGLE
  public static boolean operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "operator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPERATOR, "<operator>");
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, ASTERISK);
    if (!r) r = consumeToken(b, SLASH);
    if (!r) r = consumeToken(b, EQUAL);
    if (!r) r = consumeToken(b, NOT_EQUAL);
    if (!r) r = consumeToken(b, LTE);
    if (!r) r = consumeToken(b, GTE);
    if (!r) r = consumeToken(b, OPEN_ANGLE);
    if (!r) r = consumeToken(b, CLOSE_ANGLE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER type?
  public static boolean parameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && parameter_1(b, l + 1);
    exit_section_(b, m, PARAMETER, r);
    return r;
  }

  // type?
  private static boolean parameter_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_1")) return false;
    type(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER ASSIGN expr
  public static boolean reassign(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "reassign")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, ASSIGN);
    r = r && expr(b, l + 1);
    exit_section_(b, m, REASSIGN, r);
    return r;
  }

  /* ********************************************************** */
  // (COMMENT|(varDef SEMI)|(defCall SEMI)|(reassign SEMI)|ifDef)*
  public static boolean scope(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "scope")) return false;
    Marker m = enter_section_(b, l, _NONE_, SCOPE, "<scope>");
    while (true) {
      int c = current_position_(b);
      if (!scope_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "scope", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // COMMENT|(varDef SEMI)|(defCall SEMI)|(reassign SEMI)|ifDef
  private static boolean scope_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "scope_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMENT);
    if (!r) r = scope_0_1(b, l + 1);
    if (!r) r = scope_0_2(b, l + 1);
    if (!r) r = scope_0_3(b, l + 1);
    if (!r) r = ifDef(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // varDef SEMI
  private static boolean scope_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "scope_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = varDef(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  // defCall SEMI
  private static boolean scope_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "scope_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = defCall(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  // reassign SEMI
  private static boolean scope_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "scope_0_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = reassign(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COLON IDENTIFIER
  public static boolean type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COLON, IDENTIFIER);
    exit_section_(b, m, TYPE, r);
    return r;
  }

  /* ********************************************************** */
  // VAR IDENTIFIER type? ASSIGN expr
  public static boolean varDef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varDef")) return false;
    if (!nextTokenIs(b, VAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, VAR, IDENTIFIER);
    r = r && varDef_2(b, l + 1);
    r = r && consumeToken(b, ASSIGN);
    r = r && expr(b, l + 1);
    exit_section_(b, m, VAR_DEF, r);
    return r;
  }

  // type?
  private static boolean varDef_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varDef_2")) return false;
    type(b, l + 1);
    return true;
  }

}
