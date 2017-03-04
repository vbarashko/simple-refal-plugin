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

MULTILINE_COMMENT=(("/*"|"/**")[^"*"]{COMMENT_TAIL})|"/*"
COMMENT_TAIL=([^"*"]*("*"+[^"*""/"])?)*("*"+"/")?
END_OF_LINE_COMMENT="/""/"[^\r\n]*

CPP_INLINE_DELIMETER="%%"

STRING_LITERAL=\'([^\\\'\r\n]|{ESCAPE_SEQUENCE})*\'
ESCAPE_SEQUENCE=\\[^\r\n]

DIGIT=[0-9]
DECIMAL_INTEGER_LITERAL={DIGIT}+
INTEGER_LITERAL={DECIMAL_INTEGER_LITERAL}

%state CPP_INLINE

%%

<CPP_INLINE> {
    \n{CPP_INLINE_DELIMETER}\n    { yybegin(YYINITIAL); return SimpleRefalTypes.CPP_INLINE; }

    [^] { yybegin(CPP_INLINE); }
}

<YYINITIAL> {

    {CPP_INLINE_DELIMETER}\n        { yybegin(CPP_INLINE); }

    "$EXTERN"       { return SimpleRefalTypes.EXTERN; }
    "$ENUM"         { return SimpleRefalTypes.ENUM; }
    "$EENUM"        { return SimpleRefalTypes.EENUM; }
    "$SWAP"         { return SimpleRefalTypes.SWAP; }
    "$ESWAP"        { return SimpleRefalTypes.ESWAP; }
    "$ENTRY"        { return SimpleRefalTypes.ENTRY; }
    "$LABEL"        { return SimpleRefalTypes.LABEL; }
    "$FORWARD"      { return SimpleRefalTypes.FORWARD; }

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

    {VARIABLE_TYPE}"."{NAME_CHAR}+      { return SimpleRefalTypes.VARIABLE; }
    {FIRST_NAME_CHAR}{NAME_CHAR}*       { return SimpleRefalTypes.NAME; }

    {STRING_LITERAL}       { return SimpleRefalTypes.QUOTEDSTRING; }
    {INTEGER_LITERAL}      { return SimpleRefalTypes.INTEGER_LITERAL; }

    {MULTILINE_COMMENT}  { return SimpleRefalTypes.MULTILINE_COMMENT; }
    {END_OF_LINE_COMMENT}  { return SimpleRefalTypes.END_OF_LINE_COMMENT; }


    ({CRLF}|{WHITE_SPACE})+ { return TokenType.WHITE_SPACE; }


    .                       { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }
}