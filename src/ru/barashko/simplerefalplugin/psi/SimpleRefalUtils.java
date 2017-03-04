package ru.barashko.simplerefalplugin.psi;

import com.intellij.psi.PsiElement;

import java.util.*;

public class SimpleRefalUtils {
    public static String[] getPatternVariables(PsiElement parameter) {
        PsiElement top = parameter;
        List<String> lVariables = new ArrayList<>();
        while (top.getParent() != null) {
            if (top.toString().equals("SimpleRefalSentenceImpl(SENTENCE)")) {
                PsiElement pattern = top.getFirstChild();
                String[] tempVariables = getPatternVariablesRec(pattern);
                Collections.addAll(lVariables, tempVariables);
            }
            top = top.getParent();
        }
        String[] aVariables = new String[lVariables.size()];
        return lVariables.toArray(aVariables);
    }

    private static String[] getPatternVariablesRec(PsiElement top) {
        PsiElement[] aChildren = top.getChildren();
        List<String> lChildVariables = new ArrayList<>();
        for (PsiElement child : aChildren) {
            if (child.toString().equals("SimpleRefalVarImpl(VAR)")) {
                lChildVariables.add(child.getText());
            } else if (child.getChildren().length > 0) {
                String[] result = getPatternVariablesRec(child);
                Collections.addAll(lChildVariables, result);
            }
        }
        String[] aChildVariables = new String[lChildVariables.size()];
        return lChildVariables.toArray(aChildVariables);
    }

    public static String[] getAvailableFunctionNames(PsiElement parameters) {
        PsiElement top = parameters;

        List<String> lFunctionNames = new ArrayList<>();
        while (!top.toString().equals("SimpleRefalProgramElementImpl(PROGRAM_ELEMENT)")) {
            top = top.getParent();
        }

        PsiElement parent = top.getParent();
        for (PsiElement programElement : parent.getChildren()) {
            if (programElement.toString().equals("SimpleRefalProgramElementImpl(PROGRAM_ELEMENT)")
                    && programElement.getChildren().length > 0) {
                PsiElement functionDefinition = programElement.getFirstChild();
                if (functionDefinition.toString()
                        .equals("SimpleRefalFunctionDefinitionImpl(FUNCTION_DEFINITION)")) {
                    PsiElement[] functionDefinitionChildren = functionDefinition.getChildren();
                    for (PsiElement child : functionDefinitionChildren) {
                        if (child.toString().equals("SimpleRefalFunctionNameImpl(FUNCTION_NAME)")) {
                            lFunctionNames.add(child.getText());
                            break;
                        }
                    }
                }
            }
        }

        while (top.getParent() != null && !top.toString().equals("Simple Refal File"))
            top = top.getParent();

        Set<String> FUNC_CONTAINERS = new HashSet<>();
        FUNC_CONTAINERS.add("SimpleRefalExternalDeclarationImpl(EXTERNAL_DECLARATION)");
        FUNC_CONTAINERS.add("SimpleRefalEnumDefinitionImpl(ENUM_DEFINITION)");
        FUNC_CONTAINERS.add("SimpleRefalSwapDefinitionImpl(SWAP_DEFINITION)");
        FUNC_CONTAINERS.add("SimpleRefalSwapDefinitionImpl(SWAP_DEFINITION)");

        for (PsiElement programElement : top.getChildren()) {
            if (programElement.getChildren().length > 0) {
                if (FUNC_CONTAINERS.contains(programElement.getFirstChild().toString())) {
                    PsiElement nameList = programElement.getFirstChild().getLastChild();
                    PsiElement name = nameList.getFirstChild();
                    while (name != null) {
                        if (name.toString().equals("PsiElement(SimpleRefalTokenType.NAME)")) {
                            lFunctionNames.add(name.getText());
                        }

                        name = name.getNextSibling();
                    }
                }
            }
        }

        String[] aFunctionNames = new String[lFunctionNames.size()];
        return lFunctionNames.toArray(aFunctionNames);
    }
}
