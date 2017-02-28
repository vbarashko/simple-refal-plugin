package ru.barashko.simplerefalplugin.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import ru.barashko.simplerefalplugin.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SimpleRefalFile extends PsiFileBase {
    public SimpleRefalFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, SimpleRefalLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return SimpleRefalFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Simple Refal File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}