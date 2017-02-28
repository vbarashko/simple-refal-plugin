package ru.barashko.simplerefalplugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.*;

import javax.swing.*;

public class SimpleRefalFileType extends LanguageFileType {
    public static final SimpleRefalFileType INSTANCE = new SimpleRefalFileType();

    private SimpleRefalFileType() {
        super(SimpleRefalLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Simple Refal";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Simple Refal file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "sref";
    }

    @Override
    public boolean isReadOnly() { return false; }

    @Nullable
    @Override
    public Icon getIcon() {
        return SimpleRefalIcons.FILE;
    }
}