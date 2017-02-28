package ru.barashko.simplerefalplugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import ru.barashko.simplerefalplugin.psi.SimpleRefalTypes;
import com.intellij.psi.TokenType;

%%

%class SimpleRefalLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF= \n|\r|\r\n
WHITE_SPACE=[\ \t\f]
FIRST_NAME_CHAR=[A-Z]
NAME_CHAR=[a-zA-Z_\-0-9]
VARIABLE_TYPE = "s"|"t"|"e"

MULTILINE_COMMENT=("/*"[^"*"]{COMMENT_TAIL})|"/*"
COMMENT_TAIL=([^"*"]*("*"+[^"*""/"])?)*("*"+"/")?
END_OF_LINE_COMMENT="/""/"[^\r\n]*

MULTILINE_COMMENT2=\%\%[^\%{2}]*\%\%

STRING_LITERAL=\'([^\\\'\r\n]|{ESCAPE_SEQUENCE}|\')*\'
ESCAPE_SEQUENCE=\\[^\r\n]

DIGIT=[0-9]
DECIMAL_INTEGER_LITERAL={DIGIT}+
INTEGER_LITERAL={DECIMAL_INTEGER_LITERAL}
%%

<YYINITIAL> {

    "$EXTERN"        { yybegin(YYINITIAL); return SimpleRefalTypes.EXTERN; }
    "$ENUM"         { yybegin(YYINITIAL); return SimpleRefalTypes.ENUM; }
    "$EENUM"         { yybegin(YYINITIAL); return SimpleRefalTypes.EENUM; }
    "$SWAP"         { yybegin(YYINITIAL); return SimpleRefalTypes.SWAP; }
    "$ESWAP"         { yybegin(YYINITIAL); return SimpleRefalTypes.ESWAP; }
    "$ENTRY"         { yybegin(YYINITIAL); return SimpleRefalTypes.ENTRY; }
    "$LABEL"         { yybegin(YYINITIAL); return SimpleRefalTypes.LABEL; }
    "$FORWARD"         { yybegin(YYINITIAL); return SimpleRefalTypes.FORWARD; }


    "#" { return SimpleRefalTypes.SHARP; }
    "," { return SimpleRefalTypes.COMMA; }
    ";" { return SimpleRefalTypes.SEMICOLON; }
    "{" { return SimpleRefalTypes.LBRACE; }
    "}" { return SimpleRefalTypes.RBRACE; }
    "(" { return SimpleRefalTypes.LPAREN; }
    ")" { return SimpleRefalTypes.RPAREN; }
    "[" { return SimpleRefalTypes.LBRACKET; }
    "]" { return SimpleRefalTypes.RBRACKET; }
    "<" { return SimpleRefalTypes.LCHEVRON; }
    ">" { return SimpleRefalTypes.RCHEVRON; }
    "=" { return SimpleRefalTypes.EQUAL; }
    "^" { return SimpleRefalTypes.CARET; }
    "::" { return SimpleRefalTypes.DOUBLECOLON; }

    {VARIABLE_TYPE}"."{NAME_CHAR}+  { return SimpleRefalTypes.VARIABLE; }
    {FIRST_NAME_CHAR}{NAME_CHAR}*       { yybegin(YYINITIAL); return SimpleRefalTypes.NAME; }


    {STRING_LITERAL}       { yybegin(YYINITIAL);  return SimpleRefalTypes.QUOTEDSTRING; }
    {INTEGER_LITERAL}      { yybegin(YYINITIAL);  return SimpleRefalTypes.INTEGER_LITERAL; }


    {MULTILINE_COMMENT}  { return SimpleRefalTypes.MULTILINE_COMMENT; }
    {END_OF_LINE_COMMENT}  { return SimpleRefalTypes.END_OF_LINE_COMMENT; }

    {MULTILINE_COMMENT2}  { return SimpleRefalTypes.MULTILINE_COMMENT2; }

    ({CRLF}|{WHITE_SPACE})+ { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }


    .                       { return TokenType.BAD_CHARACTER; }
}