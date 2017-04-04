package ru.barashko.simplerefalplugin;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import ru.barashko.simplerefalplugin.psi.SimpleRefalTypes;
import ru.barashko.simplerefalplugin.psi.SimpleRefalUtils;

import java.util.Arrays;

public class SimpleRefalCompletionContributor extends CompletionContributor {
    public SimpleRefalCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(SimpleRefalTypes.VARIABLE).withLanguage(SimpleRefalLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        String[] predecessorPatternVariables = SimpleRefalUtils.getPredecessorPatternVariables(parameters.getPosition().getParent(), true);
                        String[] siblingPatternVariables = SimpleRefalUtils.getSiblingPatternVariables(parameters.getPosition().getParent());
                        String[] potentials = (String[]) ArrayUtils.addAll(predecessorPatternVariables, siblingPatternVariables);

                        System.out.println(Arrays.toString(potentials));

                        if (potentials.length > 0) {
                            for (String var : potentials) {
                                resultSet.addElement(LookupElementBuilder.create(var));
                            }
                        }
                    }
                }
        );

        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(SimpleRefalTypes.NAME).withLanguage(SimpleRefalLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        if (parameters.getPosition().getParent().
                                toString().equals("SimpleRefalFunctionNameImpl(FUNCTION_NAME)")) {
                            String[] functionNames = SimpleRefalUtils.getAvailableFunctionNames(parameters.getPosition());
                            for (String functionName : functionNames) {
                                resultSet.addElement(LookupElementBuilder.create(functionName));
                            }

                        }
                    }
                }
        );
    }


}