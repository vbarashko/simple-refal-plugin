package ru.barashko.simplerefalplugin;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.application.options.SmartIndentOptionsEditor;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.barashko.simplerefalplugin.psi.SimpleRefalUtils;

import static com.intellij.psi.codeStyle.CommonCodeStyleSettings.END_OF_LINE;

public class SimpleRefalLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {
    @NotNull
    @Override
    public Language getLanguage() {
        return SimpleRefalLanguage.INSTANCE;
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
//        if (settingsType == SettingsType.SPACING_SETTINGS) {
//            consumer.showStandardOptions("SPACE_AROUND_ASSIGNMENT_OPERATORS");
//            consumer.renameStandardOption("SPACE_AROUND_ASSIGNMENT_OPERATORS", "Separator");
//        } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
//            consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE");
//        }
        switch (settingsType) {
            default:
                break;
        }
    }

    @Override
    public IndentOptionsEditor getIndentOptionsEditor() {
        return new SmartIndentOptionsEditor();
    }

    @Nullable
    @Override
    public CommonCodeStyleSettings getDefaultCommonSettings() {
        CommonCodeStyleSettings settings = new CommonCodeStyleSettings(SimpleRefalLanguage.INSTANCE);
        settings.initIndentOptions();
        // TODO: we should define our own settings
//        settings.SPACE_AROUND_ASSIGNMENT_OPERATORS = true;
//        settings.SPACE_BEFORE_SEMICOLON = false;
//        settings.BRACE_STYLE = END_OF_LINE;
//        settings.KEEP_BLANK_LINES_IN_CODE = 2;
//        settings.KEEP_LINE_BREAKS = false;
        return settings;
    }

    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return SimpleRefalUtils.SAMPLE;
    }
}
