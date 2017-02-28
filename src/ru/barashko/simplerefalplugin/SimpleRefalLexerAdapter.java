package ru.barashko.simplerefalplugin;

import com.intellij.lexer.FlexAdapter;

class SimpleRefalLexerAdapter extends FlexAdapter {
    SimpleRefalLexerAdapter() {
        super(new SimpleRefalLexer(null));
    }
}