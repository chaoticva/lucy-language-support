package de.chaoticva.language;

import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.FlexLexer;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static de.chaoticva.language.psi.LucyTypes.*;

%%

%class LucyLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

WHITE_SPACE=[\ \n\t\f]

%state WAITING_VALUE

%%
      {WHITE_SPACE}+          { return WHITE_SPACE; }
      "#"[^\n\r]*             { return COMMENT; }
      ";"                     { return SEMI; }
      "{"                     { return OPEN_BRACE; }
      "}"                     { return CLOSE_BRACE; }
      "("                     { return OPEN_PAREN; }
      ")"                     { return CLOSE_PAREN; }
      "+"                     { return PLUS; }
      "-"                     { return MINUS; }
      "*"                     { return ASTERISK; }
      "/"                     { return SLASH; }
      "=="                    { return EQUAL; }
      "!="                    { return NOT_EQUAL; }
      "<="                    { return LTE; }
      ">="                    { return GTE; }
      "<"                     { return OPEN_ANGLE; }
      ">"                     { return CLOSE_ANGLE; }
      "="                     { return ASSIGN; }
      ","                     { return COMMA; }
      ":"                     { return COLON; }
      "var"                   { return VAR; }
      "def"                   { return DEF; }
      true|false              { return BOOLEAN; }
      [A-Za-z][A-Za-z0-9_]*   { return IDENTIFIER; }
      [0-9]+                  { return NUMBER; }
      (\")[^\"]*(\")          { return STRING; }

      [^]                     { return BAD_CHARACTER; }
