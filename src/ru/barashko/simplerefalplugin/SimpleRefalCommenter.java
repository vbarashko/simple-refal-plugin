package ru.barashko.simplerefalplugin;

import com.intellij.lang.Commenter;

/**
 * @author max
 */
public class SimpleRefalCommenter implements Commenter {
    public String getLineCommentPrefix() {
        return "//";
    }

    public String getBlockCommentPrefix() {
        return "/*";
    }

    public String getBlockCommentSuffix() {
        return "*/";
    }

    public String getCommentedBlockCommentPrefix() {
        return "/*";
    }

    public String getCommentedBlockCommentSuffix() {
        return "*/";
    }
}
