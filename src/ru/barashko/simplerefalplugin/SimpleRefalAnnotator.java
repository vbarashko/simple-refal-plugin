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
            String[] potentialVariables = SimpleRefalUtils.getPredecessorPatternVariables(element, false);
            boolean isPatternVariable = SimpleRefalUtils.isPatternVariable(element);
            boolean isInPotentialVariables = Arrays.asList(potentialVariables).contains(element.getText());
            if (!isPatternVariable && !isInPotentialVariables) {
                TextRange range = new TextRange(element.getTextRange().getStartOffset(),
                        element.getTextRange().getEndOffset());
                holder.createErrorAnnotation(range, "Unresolved variable");
            }
            if (isPatternVariable && isInPotentialVariables) {
                boolean isRedefinitionVariable = SimpleRefalUtils.isRedefinitonVariable(element);
                if (!isRedefinitionVariable) {
                    TextRange range = new TextRange(element.getTextRange().getStartOffset(),
                            element.getTextRange().getEndOffset());
                    holder.createWeakWarningAnnotation(range, "Variable is already defined");
                }
            }
            if (SimpleRefalUtils.egg(element)) {
                TextRange range = new TextRange(element.getTextRange().getStartOffset(),
                        element.getTextRange().getEndOffset());
                holder.createWarningAnnotation(range, "wa1 Waz HeЯe");
            }

        }

        if (element.toString().equals("PsiElement(SimpleRefalTokenType.NAME)") &&
                element.getParent().toString().equals("SimpleRefalFunctionNameImpl(FUNCTION_NAME)")) {
            if (!element.getParent().getParent().toString().equals("SimpleRefalFunctionDefinitionImpl(FUNCTION_DEFINITION)")) {
                String[] functionNames = SimpleRefalUtils.getAvailableFunctionNames(element);

                if (!Arrays.asList(functionNames).contains(element.getText())) {
                    TextRange range = new TextRange(element.getTextRange().getStartOffset(),
                            element.getTextRange().getEndOffset());
                    holder.createErrorAnnotation(range, "Unresolved function");
                }
            }
        }
    }
}
