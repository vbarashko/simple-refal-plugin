package ru.barashko.simplerefalplugin;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.barashko.simplerefalplugin.psi.SimpleRefalTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleRefalCompletionContributor extends CompletionContributor {
    public SimpleRefalCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(SimpleRefalTypes.VARIABLE).withLanguage(SimpleRefalLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        PsiElement top = parameters.getPosition().getParent();
                        List<PsiElement> variables = new ArrayList<>();

                        while (top.getParent() != null) {
                            if (top.toString().equals("SimpleRefalSentenceImpl(SENTENCE)")) {
                                PsiElement pattern = top.getFirstChild();
                                PsiElement[] temp_variables = getChildVariables(pattern);
                                Collections.addAll(variables, temp_variables);
                            }
                            top = top.getParent();

                        }



                        for (PsiElement var : variables) {
                            resultSet.addElement(LookupElementBuilder.create(var.getText()));
                        }

                    }
                }
        );
    }

    private PsiElement[] getChildVariables(PsiElement top) {
        PsiElement[] children = top.getChildren();
        List<PsiElement> childVariables = new ArrayList<>();

        for (PsiElement child : children) {
            if (child.getNavigationElement().toString().equals("SimpleRefalVarImpl(VAR)")) {
                childVariables.add(child);
            } else if (child.getChildren().length > 0) {
                PsiElement[] result = getChildVariables(child);
                Collections.addAll(childVariables, result);
            }
        }

        PsiElement[] childVariablesArr = new PsiElement[childVariables.size()];
        return childVariables.toArray(childVariablesArr);
    }
}