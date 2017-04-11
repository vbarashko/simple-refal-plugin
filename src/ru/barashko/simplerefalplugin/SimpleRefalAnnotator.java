package ru.barashko.simplerefalplugin;

import com.intellij.lang.annotation.*;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import ru.barashko.simplerefalplugin.psi.SimpleRefalUtils;

import java.util.Arrays;

public class SimpleRefalAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (SimpleRefalUtils.isVar(element)) {
            String[] potentialVariables = SimpleRefalUtils.getPredecessorPatternVariables(element, false);
            boolean isPatternVariable = SimpleRefalUtils.isPatternVariable(element);
            boolean isInPotentialVariables = Arrays.asList(potentialVariables).contains(element.getText());
            if (!isPatternVariable && !isInPotentialVariables) {
                holder.createErrorAnnotation(SimpleRefalUtils.getTextRange(element), "Unresolved variable");
            }
            if (isPatternVariable && isInPotentialVariables) {
                boolean isRedefinitionVariable = SimpleRefalUtils.isRedefinitonVariable(element);
                if (!isRedefinitionVariable) {
                    holder.createWeakWarningAnnotation(SimpleRefalUtils.getTextRange(element),
                            "Variable is already defined");
                }
            }
            if (SimpleRefalUtils.egg(element)) {
                holder.createWarningAnnotation(SimpleRefalUtils.getTextRange(element),
                        "\u0077\u0061\u0031\u0020\u0057\u0061\u007a\u0020\u0048\u0065\u042f\u0065");
            }

        }

        if (element.toString().equals("PsiElement(SimpleRefalTokenType.NAME)") &&
                element.getParent().toString().equals("SimpleRefalFunctionNameImpl(FUNCTION_NAME)")) {
            if (!element.getParent().getParent().toString().equals("SimpleRefalFunctionDefinitionImpl(FUNCTION_DEFINITION)")) {
                String[] functionNames = SimpleRefalUtils.getAvailableFunctionNames(element);

                if (!Arrays.asList(functionNames).contains(element.getText())) {
                    holder.createErrorAnnotation(SimpleRefalUtils.getTextRange(element), "Unresolved function");
                }
            }
        }

        if (SimpleRefalUtils.isSentence(element)) {
            if (element.getChildren().length < 2) {
                holder.createErrorAnnotation(SimpleRefalUtils.getTextRange(element), "No right hand side");
            }
        }
    }
}
