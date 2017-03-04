package ru.barashko.simplerefalplugin;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.barashko.simplerefalplugin.psi.SimpleRefalTypes;

import java.util.*;

public class SimpleRefalCompletionContributor extends CompletionContributor {
    public SimpleRefalCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(SimpleRefalTypes.VARIABLE).withLanguage(SimpleRefalLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        PsiElement top = parameters.getPosition().getParent();
                        List<PsiElement> lVariables = new ArrayList<>();
                        while (top.getParent() != null) {
                            if (top.toString().equals("SimpleRefalSentenceImpl(SENTENCE)")) {
                                PsiElement pattern = top.getFirstChild();
                                PsiElement[] tempVariables = getChildVariables(pattern);
                                Collections.addAll(lVariables, tempVariables);
                            }
                            top = top.getParent();
                        }
                        if (lVariables.size() > 0) {
                            for (PsiElement var : lVariables) {
                                resultSet.addElement(LookupElementBuilder.create(var.getText()));
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
                            PsiElement top = parameters.getPosition();
                            while (!top.toString().equals("SimpleRefalProgramElementImpl(PROGRAM_ELEMENT)")) {
                                top = top.getParent();
                            }
                            PsiElement sibling = top.getNextSibling();
                            while (sibling != null) {
                                if (sibling.toString().equals("SimpleRefalProgramElementImpl(PROGRAM_ELEMENT)")
                                        && sibling.getChildren().length > 0) {
                                    PsiElement functionDefinition = sibling.getFirstChild();
                                    if (functionDefinition.toString()
                                            .equals("SimpleRefalFunctionDefinitionImpl(FUNCTION_DEFINITION)")) {
                                        PsiElement[] functionDefinitionChildren = functionDefinition.getChildren();
                                        for (PsiElement child : functionDefinitionChildren) {
                                            if (child.toString().equals("SimpleRefalFuncNameImpl(FUNC_NAME)")) {
                                                resultSet.addElement(LookupElementBuilder.create(child.getText()));
                                                break;
                                            }
                                        }
                                    }
                                }
                                sibling = sibling.getNextSibling();
                            }

                            while (top.getParent() != null) {
                                top = top.getParent();
                            }

                            Set<String> FUNC_CONTAINERS = new HashSet<>();
                            FUNC_CONTAINERS.add("SimpleRefalExternalDeclarationImpl(EXTERNAL_DECLARATION)");
                            FUNC_CONTAINERS.add("SimpleRefalEnumDefinitionImpl(ENUM_DEFINITION)");
                            FUNC_CONTAINERS.add("SimpleRefalSwapDefinitionImpl(SWAP_DEFINITION)");

                            for (PsiElement child : top.getChildren()) {
                                if (child.getChildren().length > 0
                                        && FUNC_CONTAINERS.contains(child.getFirstChild().toString())) {
                                    PsiElement nameList = child.getFirstChild().getLastChild();
                                    PsiElement name = nameList.getFirstChild();
                                    while (name != null) {
                                        if (name.toString().equals("PsiElement(SimpleRefalTokenType.NAME)")) {
                                            resultSet.addElement(LookupElementBuilder.create(name.getText()));
                                        }

                                        name = name.getNextSibling();
                                    }
                                }
                            }
                        }
                    }
                }
        );
    }



    private PsiElement[] getChildVariables(PsiElement top) {
        PsiElement[] aChildren = top.getChildren();
        List<PsiElement> lChildVariables = new ArrayList<>();
        for (PsiElement child : aChildren) {
            if (child.toString().equals("SimpleRefalVarImpl(VAR)")) {
                lChildVariables.add(child);
            } else if (child.getChildren().length > 0) {
                PsiElement[] result = getChildVariables(child);
                Collections.addAll(lChildVariables, result);
            }
        }
        PsiElement[] aChildVariables = new PsiElement[lChildVariables.size()];
        return lChildVariables.toArray(aChildVariables);
    }
}