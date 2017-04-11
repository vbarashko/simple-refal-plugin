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

public class SimpleRefalResultImpl extends ASTWrapperPsiElement implements SimpleRefalResult {

  public SimpleRefalResultImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SimpleRefalVisitor visitor) {
    visitor.visitResult(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleRefalVisitor) accept((SimpleRefalVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<SimpleRefalResultTerm> getResultTermList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SimpleRefalResultTerm.class);
  }

}
