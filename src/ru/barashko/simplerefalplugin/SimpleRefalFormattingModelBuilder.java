package ru.barashko.simplerefalplugin;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import ru.barashko.simplerefalplugin.psi.SimpleRefalTypes;
import org.jetbrains.annotations.*;

public class SimpleRefalFormattingModelBuilder implements FormattingModelBuilder {
    @NotNull
    @Override
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(),
                new SimpleRefalBlock(element.getNode(),
                        Wrap.createWrap(WrapType.NONE, false),
                        Alignment.createAlignment(),
                        createSpaceBuilder(settings)),
                settings);
    }

    // TODO: https://github.com/ballerinalang/plugin-intellij/blob/master/src/main/java/org/ballerinalang/plugins/idea/formatter/BallerinaBlock.java

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        // TODO: fix it
        return new SpacingBuilder(settings, SimpleRefalLanguage.INSTANCE)
                .around(SimpleRefalTypes.SEMICOLON)
                .spaceIf(settings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
                .before(SimpleRefalTypes.SENTENCE)
                .none();
    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }
}