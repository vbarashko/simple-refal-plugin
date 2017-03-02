// This is a generated file. Not intended for manual editing.
package ru.barashko.simplerefalplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import ru.barashko.simplerefalplugin.psi.impl.*;

public interface SimpleRefalTypes {

  IElementType BLOCK = new SimpleRefalElementType("BLOCK");
  IElementType COMMENT = new SimpleRefalElementType("COMMENT");
  IElementType COMMON_TERM = new SimpleRefalElementType("COMMON_TERM");
  IElementType ENUM_DEFINITION = new SimpleRefalElementType("ENUM_DEFINITION");
  IElementType EXTERNAL_DECLARATION = new SimpleRefalElementType("EXTERNAL_DECLARATION");
  IElementType FORWARD_DECRATION = new SimpleRefalElementType("FORWARD_DECRATION");
  IElementType FUNCTION_DEFINITION = new SimpleRefalElementType("FUNCTION_DEFINITION");
  IElementType IDENTIFIER = new SimpleRefalElementType("IDENTIFIER");
  IElementType IDENTIFIER_DEFINITION = new SimpleRefalElementType("IDENTIFIER_DEFINITION");
  IElementType NAME_LIST = new SimpleRefalElementType("NAME_LIST");
  IElementType PATTERN = new SimpleRefalElementType("PATTERN");
  IElementType PATTERN_TERM = new SimpleRefalElementType("PATTERN_TERM");
  IElementType PROGRAM_ELEMENT = new SimpleRefalElementType("PROGRAM_ELEMENT");
  IElementType REDEFINITION_VARIABLE = new SimpleRefalElementType("REDEFINITION_VARIABLE");
  IElementType RESULT = new SimpleRefalElementType("RESULT");
  IElementType RESULT_TERM = new SimpleRefalElementType("RESULT_TERM");
  IElementType SENTENCE = new SimpleRefalElementType("SENTENCE");
  IElementType SWAP_DEFINITION = new SimpleRefalElementType("SWAP_DEFINITION");
  IElementType VAR = new SimpleRefalElementType("VAR");

  IElementType CARET = new SimpleRefalTokenType("CARET");
  IElementType COMMA = new SimpleRefalTokenType("COMMA");
  IElementType DOUBLECOLON = new SimpleRefalTokenType("DOUBLECOLON");
  IElementType EENUM = new SimpleRefalTokenType("EENUM");
  IElementType END_OF_LINE_COMMENT = new SimpleRefalTokenType("END_OF_LINE_COMMENT");
  IElementType ENTRY = new SimpleRefalTokenType("ENTRY");
  IElementType ENUM = new SimpleRefalTokenType("ENUM");
  IElementType EQUAL = new SimpleRefalTokenType("EQUAL");
  IElementType ESWAP = new SimpleRefalTokenType("ESWAP");
  IElementType EXTERN = new SimpleRefalTokenType("EXTERN");
  IElementType FORWARD = new SimpleRefalTokenType("FORWARD");
  IElementType INTEGER_LITERAL = new SimpleRefalTokenType("INTEGER_LITERAL");
  IElementType LABEL = new SimpleRefalTokenType("LABEL");
  IElementType LBRACE = new SimpleRefalTokenType("LBRACE");
  IElementType LBRACKET = new SimpleRefalTokenType("LBRACKET");
  IElementType LCHEVRON = new SimpleRefalTokenType("LCHEVRON");
  IElementType LPAREN = new SimpleRefalTokenType("LPAREN");
  IElementType MULTILINE_COMMENT = new SimpleRefalTokenType("MULTILINE_COMMENT");
  IElementType MULTILINE_COMMENT2 = new SimpleRefalTokenType("MULTILINE_COMMENT2");
  IElementType NAME = new SimpleRefalTokenType("NAME");
  IElementType QUOTEDSTRING = new SimpleRefalTokenType("QUOTEDSTRING");
  IElementType RBRACE = new SimpleRefalTokenType("RBRACE");
  IElementType RBRACKET = new SimpleRefalTokenType("RBRACKET");
  IElementType RCHEVRON = new SimpleRefalTokenType("RCHEVRON");
  IElementType RPAREN = new SimpleRefalTokenType("RPAREN");
  IElementType SEMICOLON = new SimpleRefalTokenType("SEMICOLON");
  IElementType SHARP = new SimpleRefalTokenType("SHARP");
  IElementType SWAP = new SimpleRefalTokenType("SWAP");
  IElementType VARIABLE = new SimpleRefalTokenType("VARIABLE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == BLOCK) {
        return new SimpleRefalBlockImpl(node);
      }
      else if (type == COMMENT) {
        return new SimpleRefalCommentImpl(node);
      }
      else if (type == COMMON_TERM) {
        return new SimpleRefalCommonTermImpl(node);
      }
      else if (type == ENUM_DEFINITION) {
        return new SimpleRefalEnumDefinitionImpl(node);
      }
      else if (type == EXTERNAL_DECLARATION) {
        return new SimpleRefalExternalDeclarationImpl(node);
      }
      else if (type == FORWARD_DECRATION) {
        return new SimpleRefalForwardDecrationImpl(node);
      }
      else if (type == FUNCTION_DEFINITION) {
        return new SimpleRefalFunctionDefinitionImpl(node);
      }
      else if (type == IDENTIFIER) {
        return new SimpleRefalIdentifierImpl(node);
      }
      else if (type == IDENTIFIER_DEFINITION) {
        return new SimpleRefalIdentifierDefinitionImpl(node);
      }
      else if (type == NAME_LIST) {
        return new SimpleRefalNameListImpl(node);
      }
      else if (type == PATTERN) {
        return new SimpleRefalPatternImpl(node);
      }
      else if (type == PATTERN_TERM) {
        return new SimpleRefalPatternTermImpl(node);
      }
      else if (type == PROGRAM_ELEMENT) {
        return new SimpleRefalProgramElementImpl(node);
      }
      else if (type == REDEFINITION_VARIABLE) {
        return new SimpleRefalRedefinitionVariableImpl(node);
      }
      else if (type == RESULT) {
        return new SimpleRefalResultImpl(node);
      }
      else if (type == RESULT_TERM) {
        return new SimpleRefalResultTermImpl(node);
      }
      else if (type == SENTENCE) {
        return new SimpleRefalSentenceImpl(node);
      }
      else if (type == SWAP_DEFINITION) {
        return new SimpleRefalSwapDefinitionImpl(node);
      }
      else if (type == VAR) {
        return new SimpleRefalVarImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
