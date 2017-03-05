package ru.barashko.simplerefalplugin;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import ru.barashko.simplerefalplugin.psi.SimpleRefalTypes;

import java.util.HashSet;
import java.util.Set;

import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*;
import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

class SimpleRefalSyntaxHighlighter extends SyntaxHighlighterBase {
    static final TextAttributesKey SR_KEYWORD =
            createTextAttributesKey("SIMPLE_REFAL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    static final TextAttributesKey SR_NAME =
            createTextAttributesKey("SIMPLE_REFAL_NAME", DefaultLanguageHighlighterColors.IDENTIFIER);
    static final TextAttributesKey SR_SEMICOLON =
            createTextAttributesKey("SIMPLE_REFAL_SEMICOLON", SEMICOLON);
    static final TextAttributesKey SR_FUNCTION_NAME =
            createTextAttributesKey("SIMPLE_REFAL_FUNCTION_NAME", FUNCTION_DECLARATION);
    static final TextAttributesKey SR_COMMENT =
            createTextAttributesKey("SIMPLE_REFAL_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    static final TextAttributesKey SR_NUMBER =
            createTextAttributesKey("SIMPLE_REFAL_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    static final TextAttributesKey SR_STRING =
            createTextAttributesKey("SIMPLE_REFAL_STRING", DefaultLanguageHighlighterColors.STRING);
    static final TextAttributesKey SR_VARIABLE =
            createTextAttributesKey("SIMPLE_REFAL_VARIABLE", DefaultLanguageHighlighterColors.INSTANCE_FIELD);

    private static Set<IElementType> KEYWORD_ELEMENTS = new HashSet<>();
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

    private static Set<IElementType> COMMENT_ELEMENTS = new HashSet<>();
    static {
        COMMENT_ELEMENTS.add(SimpleRefalTypes.MULTILINE_COMMENT);
        COMMENT_ELEMENTS.add(SimpleRefalTypes.END_OF_LINE_COMMENT);
        COMMENT_ELEMENTS.add(SimpleRefalTypes.CPP_INLINE);

    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new SimpleRefalLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType type) {
        if (COMMENT_ELEMENTS.contains(type)) {
            return pack(SR_COMMENT);
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
            return pack(SR_SEMICOLON);
        }
        if (KEYWORD_ELEMENTS.contains(type)) {
            return pack(SR_KEYWORD);
        }
        return new TextAttributesKey[0];

    }

}