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
            String[] potentialVariables = SimpleRefalUtils.getPatternVariables(element, false);
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
                    holder.createWarningAnnotation(range, "Variable is already defined");
                }
            }


        }

        if (element.toString().equals("PsiElement(SimpleRefalTokenType.NAME)") &&
                element.getParent().toString().equals("SimpleRefalFunctionNameImpl(FUNCTION_NAME)")) {
            if (element.getParent().getParent().toString().equals("SimpleRefalFunctionDefinitionImpl(FUNCTION_DEFINITION)")) {
                TextRange range = new TextRange(element.getTextRange().getStartOffset(),
                        element.getTextRange().getEndOffset());
                holder.createInfoAnnotation(range, null).setTextAttributes(SimpleRefalSyntaxHighlighter.SR_FUNCTION_NAME);
            } else {
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
