package ru.barashko.simplerefalplugin;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.barashko.simplerefalplugin.psi.SimpleRefalTypes;
import ru.barashko.simplerefalplugin.psi.SimpleRefalUtils;

public class SimpleRefalCompletionContributor extends CompletionContributor {
    public SimpleRefalCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(SimpleRefalTypes.VARIABLE).withLanguage(SimpleRefalLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        if (SimpleRefalUtils.isPatternVariable(parameters.getPosition().getParent()))
                            return;

                        String[] variables = SimpleRefalUtils.getPatternVariables(parameters.getPosition().getParent(), true);
                        if (variables.length > 0) {
                            for (String var : variables) {
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