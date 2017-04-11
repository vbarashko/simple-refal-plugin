package ru.barashko.simplerefalplugin;

import com.intellij.application.options.*;
import com.intellij.openapi.options.Configurable;
import com.intellij.psi.codeStyle.*;
import org.jetbrains.annotations.*;

public class SimpleRefalCodeStyleSettingsProvider extends CodeStyleSettingsProvider {
    @Override
    public CustomCodeStyleSettings createCustomSettings(CodeStyleSettings settings) {
        return new SimpleRefalCodeStyleSettings(settings);
    }

    @Nullable
    @Override
    public String getConfigurableDisplayName() {
        return "Simple Refal";
    }

    @NotNull
    @Override
    public Configurable createSettingsPage(CodeStyleSettings settings, CodeStyleSettings originalSettings) {
        return new CodeStyleAbstractConfigurable(settings, originalSettings, "Simple") {
            @Override
            protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings) {
                return new SimpleCodeStyleMainPanel(getCurrentSettings(), settings);
            }

            @Nullable
            @Override
            public String getHelpTopic() {
                return null;
            }
        };
    }

    private static class SimpleCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {
        public SimpleCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
            super(SimpleRefalLanguage.INSTANCE, currentSettings, settings);
        }
    }
}