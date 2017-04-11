package ru.barashko.simplerefalplugin.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Contract;

import java.util.*;

public class SimpleRefalUtils {

    public static final String SAMPLE = "/*\n" +
            "  Multiple line comments are allowed.\n" +
            "*/\n" +
            "\n" +
            "// And single line comments too.\n" +
            "\n" +
            "//FROM Library\n" +
            "$EXTERN WriteLine;\n" +
            "\n" +
            "//FROM LibraryEx\n" +
            "$EXTERN SaveFile, LoadFile, ArgList;\n" +
            "\n" +
            "$ENUM Encapsulated;\n" +
            "\n" +
            "$EENUM ExportedEnum;\n" +
            "$SWAP LocalVariable;\n" +
            "$ESWAP GlobalVariable;\n" +
            "$LABEL Success, Fails;  // Deprecated keyword\n" +
            "\n" +
            "$ENTRY CreateEncapsulated {\n" +
            "  /* comment */ = #Fails;\n" +
            "\n" +
            "  e.X = [Encapsulated e.X] #Success;\n" +
            "}\n" +
            "\n" +
            "$FORWARD Main;  // Previously it was mandatory, now it is ignored\n" +
            "\n" +
            "$ENTRY Go {\n" +
            "  = <Main <ArgList>>;\n" +
            "}\n" +
            "\n" +
            "%%\n" +
            "void f() {\n" +
            "  // C++ inline code\n" +
            "  for (int i = 0; i < 100; ++i)\n" +
            "    printf(\"%i = 0x%x\n\", i);\n" +
            "\n" +
            "  bool x = 1 < 2 && 3 > 1;\n" +
            "}\n" +
            "%%\n" +
            "\n" +
            "Main {\n" +
            "  (e.ProgName) =\n" +
            "    <WriteLine 'Arguments not found' 10 12>\n" +
            "\n" +
            "  (e.ProgName) e.Files =\n" +
            "    <Map\n" +
            "      {\n" +
            "        (e.NextFile) =\n" +
            "          <SaveFile\n" +
            "            (e.NextFile '.out')\n" +
            "            <LoadFile e.NextFile>\n" +
            "          >;\n" +
            "      }\n" +
            "      e.Files\n" +
            "    >;\n" +
            "}\n" +
            "\n" +
            "$ENTRY NativeFunction {\n" +
            "%%\n" +
            "  // Inline code could be inside functions\n" +
            "  return refalrts::cRecognitionImpossible;\n" +
            "%%\n" +
            "}\n";

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
        ELEMENTS.add("\u0076\u006f\u0076\u0061\u006e");
        ELEMENTS.add("\u0076\u0061\u0076\u0061\u006e");
        ELEMENTS.add("\u0077\u0061\u0076\u0061\u006e");
        ELEMENTS.add("\u0077\u0061\u0031");
        return ELEMENTS.contains(name.substring(2).toLowerCase());
    }

    public static boolean isSentence(PsiElement element) {
        return element.toString().equals("SimpleRefalSentenceImpl(SENTENCE)");
    }

    public static boolean isVar(PsiElement element) {
        return element.toString().equals("SimpleRefalVarImpl(VAR)");
    }

    @Contract("_ -> !null")
    public static TextRange getTextRange(PsiElement element) {
        return new TextRange(element.getTextRange().getStartOffset(),
                element.getTextRange().getEndOffset());
    }
}
