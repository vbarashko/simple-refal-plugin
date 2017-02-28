package ru.barashko.simplerefalplugin;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.*;
import org.jetbrains.annotations.*;

import javax.swing.*;
import java.util.Map;

public class SimpleRefalColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Keyword", SimpleRefalSyntaxHighlighter.SR_KEYWORD),
            new AttributesDescriptor("Name", SimpleRefalSyntaxHighlighter.SR_NAME),
            new AttributesDescriptor("Function Declaration", SimpleRefalSyntaxHighlighter.SR_FUNCTION_NAME),
            new AttributesDescriptor("Variable", SimpleRefalSyntaxHighlighter.SR_VARIABLE)
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
        return "$EXTERN A, B, C;\n\n" +
                "$ENUM L;\n" +
                "$EENUM E;\n\n" +
                "$ENTRY Map {\n" + "    s.Func t.Next e.Tail = <<s.Func e.Tail> e.Tail>;\n" + "}\n" +
                "Plain {\n" + "    e.Brackets = <Map { (e.X) = e.X; } e.Brackets>;\n" + "}";
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