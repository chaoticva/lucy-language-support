package de.chaoticva.language.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import de.chaoticva.language.LucyFileType;
import de.chaoticva.language.LucyLanguage;
import org.jetbrains.annotations.NotNull;

public class LucyFile extends PsiFileBase {
    public LucyFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, LucyLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return LucyFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Lucy Class File";
    }
}
