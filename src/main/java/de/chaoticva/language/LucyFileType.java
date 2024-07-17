package de.chaoticva.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class LucyFileType extends LanguageFileType {
    public static final LucyFileType INSTANCE = new LucyFileType();

    protected LucyFileType() {
        super(LucyLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "Lucy Class File";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "Lucy Language Class File";
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return ".lc";
    }

    @Override
    public Icon getIcon() {
        return LucyIcons.FILE;
    }
}
