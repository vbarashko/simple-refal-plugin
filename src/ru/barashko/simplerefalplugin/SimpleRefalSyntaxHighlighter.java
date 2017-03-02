package ru.barashko.simplerefalplugin;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.ui.JBColor;
import ru.barashko.simplerefalplugin.psi.SimpleRefalTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*;

class SimpleRefalSyntaxHighlighter extends SyntaxHighlighterBase {
    static final TextAttributesKey SR_KEYWORD = createTextAttributesKey("SIMPLE_REFAL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    static final TextAttributesKey SR_NAME = createTextAttributesKey("SIMPLE_REFAL_NAME", DefaultLanguageHighlighterColors.IDENTIFIER);
    static final TextAttributesKey SR_SEMICOLON = createTextAttributesKey("SIMPLE_REFAL_SEMICOLON", SEMICOLON);
    static final TextAttributesKey SR_COMMA = createTextAttributesKey("SIMPLE_REFAL_COMMA", COMMA);
    static final TextAttributesKey SR_FUNCTION_NAME = createTextAttributesKey("SIMPLE_REFAL_FUNCTION_NAME", FUNCTION_DECLARATION);
    static final TextAttributesKey SR_LINE_COMMENT = createTextAttributesKey("SIMPLE_REFAL_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    static final TextAttributesKey SR_BLOCK_COMMENT = createTextAttributesKey("SIMPLE_REFAL_BLOCK_COMMENT", DefaultLanguageHighlighterColors.DOC_COMMENT_MARKUP);
    static final TextAttributesKey SR_NUMBER = createTextAttributesKey("SIMPLE_REFAL_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    static final TextAttributesKey SR_STRING = createTextAttributesKey("SIMPLE_REFAL_STRING", DefaultLanguageHighlighterColors.STRING);
    static final TextAttributesKey SR_VARIABLE = createTextAttributesKey("SIMPLE_REFAL_VARIABLE", DefaultLanguageHighlighterColors.INSTANCE_FIELD);


    static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    public static Set<IElementType> KEYWORD_ELEMENTS = new HashSet<IElementType>();
    static {
        KEYWORD_ELEMENTS.add(SimpleRefalTypes.EXTERN);
        KEYWORD_ELEMENTS.add(SimpleRefalTypes.ENUM);
        KEYWORD_ELEMENTS.add(SimpleRefalTypes.EENUM);
        KEYWORD_ELEMENTS.add(SimpleRefalTypes.SWAP);
        KEYWORD_ELEMENTS.add(SimpleRefalTypes.ESWAP);
        KEYWORD_ELEMENTS.add(SimpleRefalTypes.ENTRY);
        KEYWORD_ELEMENTS.add(SimpleRefalTypes.LABEL);
        KEYWORD_ELEMENTS.add(SimpleRefalTypes.FORWARD);

    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new SimpleRefalLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType type) {
        if (type == SimpleRefalTypes.MULTILINE_COMMENT || type == SimpleRefalTypes.MULTILINE_COMMENT2) {
            return pack(SR_BLOCK_COMMENT);
        }
        if (type == SimpleRefalTypes.END_OF_LINE_COMMENT) {
            return pack(SR_LINE_COMMENT);
        }
        if (type.equals(SimpleRefalTypes.NAME)) {
            return pack(SR_NAME);
        }
        if (type.equals(SimpleRefalTypes.QUOTEDSTRING)) {
            return pack(SR_STRING);
        }
        if (type.equals(SimpleRefalTypes.INTEGER_LITERAL)) {
            return pack(SR_NUMBER);
        }
        if (type.equals(SimpleRefalTypes.VARIABLE)) {
            return pack(SR_VARIABLE);
        }
        if (type.equals(SimpleRefalTypes.SEMICOLON)) {
            return pack(SR_SEMICOLON);
        }
        if (type.equals(SimpleRefalTypes.COMMA)) {
            return pack(SR_COMMA);
        }
        if (KEYWORD_ELEMENTS.contains(type)) {
            return pack(SR_KEYWORD);
        }
        return EMPTY_KEYS;

    }

}