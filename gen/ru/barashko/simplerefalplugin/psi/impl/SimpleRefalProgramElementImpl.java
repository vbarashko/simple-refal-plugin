// This is a generated file. Not intended for manual editing.
package ru.barashko.simplerefalplugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ru.barashko.simplerefalplugin.psi.SimpleRefalTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import ru.barashko.simplerefalplugin.psi.*;

public class SimpleRefalProgramElementImpl extends ASTWrapperPsiElement implements SimpleRefalProgramElement {

  public SimpleRefalProgramElementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SimpleRefalVisitor visitor) {
    visitor.visitProgramElement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleRefalVisitor) accept((SimpleRefalVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SimpleRefalEnumDefinition getEnumDefinition() {
    return findChildByClass(SimpleRefalEnumDefinition.class);
  }

  @Override
  @Nullable
  public SimpleRefalExternalDeclaration getExternalDeclaration() {
    return findChildByClass(SimpleRefalExternalDeclaration.class);
  }

  @Override
  @Nullable
  public SimpleRefalForwardDecration getForwardDecration() {
    return findChildByClass(SimpleRefalForwardDecration.class);
  }

  @Override
  @Nullable
  public SimpleRefalFunctionDefinition getFunctionDefinition() {
    return findChildByClass(SimpleRefalFunctionDefinition.class);
  }

  @Override
  @Nullable
  public SimpleRefalIdentifierDefinition getIdentifierDefinition() {
    return findChildByClass(SimpleRefalIdentifierDefinition.class);
  }

  @Override
  @Nullable
  public SimpleRefalSwapDefinition getSwapDefinition() {
    return findChildByClass(SimpleRefalSwapDefinition.class);
  }

}
