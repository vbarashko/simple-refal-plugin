package ru.barashko.simplerefalplugin;

import com.intellij.lang.annotation.*;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import ru.barashko.simplerefalplugin.psi.SimpleRefalUtils;

import java.util.Arrays;

public class SimpleRefalAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (element.toString().equals("SimpleRefalVarImpl(VAR)")) {
            String[] potentialVariables = SimpleRefalUtils.getPatternVariables(element);
            if (!Arrays.asList(potentialVariables).contains(element.getText())) {
                TextRange range = new TextRange(element.getTextRange().getStartOffset(),
                        element.getTextRange().getEndOffset());
                holder.createErrorAnnotation(range, "Unresolved variable");
            }
        }
    }
}
