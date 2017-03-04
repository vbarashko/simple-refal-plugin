package ru.barashko.simplerefalplugin.psi;

import com.intellij.psi.PsiElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
