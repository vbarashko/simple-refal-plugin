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
        return "/*\n" +
                "  Simple Refal color highlighting\n" +
                "  Multiple line comments are allowed.\n" +
                "*/\n" +
                "\n" +
                "// And single line comments too.\n" +
                "\n" +
                "//FROM Library\n" +
                "$EXTERN WriteLine;\n" +
                "\n" +
                "//FROM LibraryEx\n" +
                "$EXTERN SaveFile, LoadFile, ArgList;\n" +
                "\n" +
                "$ENUM Encapsulated;\n" +
                "\n" +
                "$EENUM ExportedEnum;\n" +
                "$SWAP LocalVariable;\n" +
                "$ESWAP GlobalVariable;\n" +
                "$LABEL Success, Fails;  // Deprecated keyword\n" +
                "\n" +
                "$ENTRY CreateEncapsulated {\n" +
                "  /* comment */ = #Fails;\n" +
                "\n" +
                "  e.X = [Encapsulated e.X] #Success;\n" +
                "}\n" +
                "\n" +
                "$FORWARD Main;  // Previously it was mandatory, now it is ignored\n" +
                "\n" +
                "$ENTRY Go {\n" +
                "  = <Main <ArgList>>;\n" +
                "}\n" +
                "\n" +
                "%%\n" +
                "void f() {\n" +
                "  // C++ inline code\n" +
                "  for (int i = 0; i < 100; ++i)\n" +
                "    printf(\"%i = 0x%x\n\", i);\n" +
                "\n" +
                "  bool x = 1 < 2 && 3 > 1;\n" +
                "}\n" +
                "%%\n" +
                "\n" +
                "Main {\n" +
                "  (e.ProgName) =\n" +
                "    <WriteLine 'Arguments not found' 10 12>\n" +
                "\n" +
                "  (e.ProgName) e.Files =\n" +
                "    <Map\n" +
                "      {\n" +
                "        (e.NextFile) =\n" +
                "          <SaveFile\n" +
                "            (e.NextFile '.out')\n" +
                "            <LoadFile e.NextFile>\n" +
                "          >;\n" +
                "      }\n" +
                "      e.Files\n" +
                "    >;\n" +
                "}\n" +
                "\n" +
                "$ENTRY NativeFunction {\n" +
                "%%\n" +
                "  // Inline code could be inside functions\n" +
                "  return refalrts::cRecognitionImpossible;\n" +
                "%%\n" +
                "}\n";
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