package ru.barashko.simplerefalplugin.psi;

import com.intellij.psi.PsiElement;

import java.util.*;

public class SimpleRefalUtils {
    public static String[] getPredecessorPatternVariables(PsiElement element, boolean includePredecessorPattern) {
        PsiElement top = element;
        List<String> lVariables = new ArrayList<>();
        boolean isMoreThanPredecessor = includePredecessorPattern;

        while (top.getParent() != null) {
            if (isSentence(top.getParent())) {
                boolean isPattern = top.toString().equals("SimpleRefalPatternImpl(PATTERN)");
                if (!isPattern || isMoreThanPredecessor) {
                    String[] tempVariables = getVariablesRec(top.getParent().getFirstChild());
                    Collections.addAll(lVariables, tempVariables);
                    isMoreThanPredecessor = true;
                }
            }
            top = top.getParent();
        }
        String[] aVariables = new String[lVariables.size()];
        return lVariables.toArray(aVariables);
    }

    private static String[] getVariablesRec(PsiElement top) {
        PsiElement[] aChildren = top.getChildren();
        List<String> lChildVariables = new ArrayList<>();
        for (PsiElement child : aChildren) {
            if (child.toString().equals("SimpleRefalVarImpl(VAR)")) {
                lChildVariables.add(child.getText());
            } else if (child.getChildren().length > 0) {
                String[] result = getVariablesRec(child);
                Collections.addAll(lChildVariables, result);
            }
        }
        String[] aChildVariables = new String[lChildVariables.size()];
        return lChildVariables.toArray(aChildVariables);
    }



    public static String[] getSiblingPatternVariables(PsiElement element) {
        PsiElement top = element;
        List<String> lVariables = new ArrayList<>();
        while (top != null) {
            if (isSentence(top)) {
                break;
            }
            top = top.getParent();
        }

        if (top == null)
            return null;

        for (PsiElement sentence : top.getParent().getChildren()) {
            if (isSentence(sentence)) {
                PsiElement pattern = sentence.getFirstChild();
                String[] tempVariables = getVariablesRec(pattern);
                Collections.addAll(lVariables, tempVariables);
            }
        }

        String[] aVariables = new String[lVariables.size()];
        return lVariables.toArray(aVariables);
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

    public static boolean isPatternVariable(PsiElement element) {
        PsiElement top = element;
        if (!element.toString().equals("SimpleRefalVarImpl(VAR)"))
            return false;

        while (top.getParent() != null) {
            if (top.toString().equals("SimpleRefalPatternImpl(PATTERN)"))
                return true;

            if (top.toString().equals("SimpleRefalResultImpl(RESULT)")) {
                return false;
            }
            top = top.getParent();
        }
        return false;
    }

    public static boolean isRedefinitonVariable(PsiElement element) {
        if (!element.toString().equals("SimpleRefalVarImpl(VAR)"))
            return false;

        if (element.getParent().toString().equals("SimpleRefalRedefinitionVariableImpl(REDEFINITION_VARIABLE)"))
            return true;

        return false;
    }

    public static boolean egg(PsiElement element) {
        String name = element.getText();
        Set<String> ELEMENTS = new HashSet<>();
        ELEMENTS.add("vovan");
        ELEMENTS.add("vavan");
        ELEMENTS.add("wavan");
        ELEMENTS.add("wa1");
        return ELEMENTS.contains(name.substring(2));
    }

    private static boolean isSentence(PsiElement element) {
        return element.toString().equals("SimpleRefalSentenceImpl(SENTENCE)");
    }
}
