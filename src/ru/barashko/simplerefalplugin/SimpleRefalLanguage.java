package ru.barashko.simplerefalplugin;

import com.intellij.lang.Language;

public class SimpleRefalLanguage extends Language {
    public static final Language INSTANCE = new SimpleRefalLanguage();

    private SimpleRefalLanguage() {
        super("Simple Refal");
    }
}