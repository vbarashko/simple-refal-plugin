// This is a generated file. Not intended for manual editing.
package ru.barashko.simplerefalplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SimpleRefalProgramElement extends PsiElement {

  @Nullable
  SimpleRefalEnumDefinition getEnumDefinition();

  @Nullable
  SimpleRefalExternalDeclaration getExternalDeclaration();

  @Nullable
  SimpleRefalForwardDecration getForwardDecration();

  @Nullable
  SimpleRefalFunctionDefinition getFunctionDefinition();

  @Nullable
  SimpleRefalIdentifierDefinition getIdentifierDefinition();

  @Nullable
  SimpleRefalSwapDefinition getSwapDefinition();

}
