package ru.barashko.simplerefalplugin;

import com.intellij.lang.*;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.tree.*;
import ru.barashko.simplerefalplugin.parser.SimpleRefalParser;
import ru.barashko.simplerefalplugin.psi.*;
import org.jetbrains.annotations.NotNull;

public class SimpleRefalParserDefinition implements ParserDefinition {
    private static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    private static final TokenSet COMMENTS = TokenSet.create(SimpleRefalTypes.COMMENT);

    private static final IFileElementType FILE =
            new IFileElementType(Language.findInstance(SimpleRefalLanguage.class));

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new SimpleRefalLexerAdapter();
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return SimpleRefalTokenType.getCommentSet();
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.create(SimpleRefalTypes.QUOTEDSTRING);
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new SimpleRefalParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new SimpleRefalFile(viewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return SimpleRefalTypes.Factory.createElement(node);
    }
}