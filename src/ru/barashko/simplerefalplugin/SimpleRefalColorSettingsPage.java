package ru.barashko.simplerefalplugin;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.*;
import org.jetbrains.annotations.*;
import ru.barashko.simplerefalplugin.psi.SimpleRefalUtils;

import javax.swing.*;
import java.util.Map;

public class SimpleRefalColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Keyword", SimpleRefalSyntaxHighlighter.SR_KEYWORD),
            new AttributesDescriptor("Name", SimpleRefalSyntaxHighlighter.SR_NAME),
            new AttributesDescriptor("Function Declaration", SimpleRefalSyntaxHighlighter.SR_FUNCTION_NAME),
            new AttributesDescriptor("Variable", SimpleRefalSyntaxHighlighter.SR_VARIABLE),
            new AttributesDescriptor("Semicolon and Comma", SimpleRefalSyntaxHighlighter.SR_SEMICOLON),
            new AttributesDescriptor("Comments and C++ inline code", SimpleRefalSyntaxHighlighter.SR_COMMENT),
            new AttributesDescriptor("Number", SimpleRefalSyntaxHighlighter.SR_NUMBER),
            new AttributesDescriptor("String", SimpleRefalSyntaxHighlighter.SR_STRING)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return SimpleRefalIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new SimpleRefalSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return SimpleRefalUtils.SAMPLE;
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Simple Refal";
    }
}