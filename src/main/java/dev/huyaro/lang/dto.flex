package dev.huyaro.lang.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static dev.huyaro.lang.psi.DtoTypes.*;

%%

%{
  public _DtoLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _DtoLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

SPACE=[ \t\n\x0B\f\r]+
ID=[a-zA-Z_0-9]+
STRINGLITERAL=(\"([\u4e00-\u9fa5\uff00-\uffff]|[^\"])*\")|('[a-zA-Z0-9_.]*')
NUMBER=-?(0|[1-9][0-9]*)(\.[0-9]+)?([e|E][+-]?[0-9]+)?
COMMENT="/"\*(.|\n)*?\*"/"|"//".*\n

%%
<YYINITIAL> {
  {WHITE_SPACE}         { return WHITE_SPACE; }

  "{"                   { return LBRACE; }
  "}"                   { return RBRACE; }
  "["                   { return LBRACK; }
  "]"                   { return RBRACK; }
  "("                   { return LPAREN; }
  ")"                   { return RPAREN; }
  "<"                   { return LANGLE; }
  ">"                   { return RANGLE; }
  ","                   { return COMMA; }
  ";"                   { return SEMICOLON; }
  "."                   { return DOT; }
  "@"                   { return AT; }
  "#"                   { return HASH; }
  "="                   { return EQUAL; }
  "?"                   { return OPTNULL; }
  "^"                   { return CARET; }
  "$"                   { return DOLOR; }
  ":"                   { return COLON; }
  "*"                   { return STAR; }
  "-"                   { return MINUS; }
  "->"                  { return RIGHT_ARROW; }
  "abstract"            { return ABSTRACT; }
  "input"               { return INPUT; }
  "import"              { return IMPORT; }
  "as"                  { return KEY_AS; }

  {SPACE}               { return SPACE; }
  {ID}                  { return ID; }
  {STRINGLITERAL}       { return STRINGLITERAL; }
  {NUMBER}              { return NUMBER; }
  {COMMENT}             { return COMMENT; }

}

[^] { return BAD_CHARACTER; }
