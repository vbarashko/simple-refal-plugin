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

public class SimpleRefalPatternTermImpl extends ASTWrapperPsiElement implements SimpleRefalPatternTerm {

  public SimpleRefalPatternTermImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SimpleRefalVisitor visitor) {
    visitor.visitPatternTerm(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleRefalVisitor) accept((SimpleRefalVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SimpleRefalCommonTerm getCommonTerm() {
    return findChildByClass(SimpleRefalCommonTerm.class);
  }

  @Override
  @Nullable
  public SimpleRefalPattern getPattern() {
    return findChildByClass(SimpleRefalPattern.class);
  }

  @Override
  @Nullable
  public SimpleRefalRedefinitionVariable getRedefinitionVariable() {
    return findChildByClass(SimpleRefalRedefinitionVariable.class);
  }

}
