package ru.barashko.simplerefalplugin.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.*;
import ru.barashko.simplerefalplugin.*;

public class SimpleRefalElementType extends IElementType {
    SimpleRefalElementType(@NotNull @NonNls String debugName) {
        super(debugName, SimpleRefalLanguage.INSTANCE);
    }
}