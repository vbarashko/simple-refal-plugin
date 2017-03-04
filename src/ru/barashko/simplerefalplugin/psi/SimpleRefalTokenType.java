package ru.barashko.simplerefalplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.*;
import ru.barashko.simplerefalplugin.*;

public class SimpleRefalTokenType extends IElementType {

    SimpleRefalTokenType(@NotNull @NonNls String debugName) {
        super(debugName, SimpleRefalLanguage.INSTANCE);
    }

    public static TokenSet getCommentSet() {
        return TokenSet.create(SimpleRefalTypes.CPP_INLINE, SimpleRefalTypes.MULTILINE_COMMENT,
                SimpleRefalTypes.END_OF_LINE_COMMENT);
    }

    @Override
    public String toString() {
        return "SimpleRefalTokenType." + super.toString();
    }
}